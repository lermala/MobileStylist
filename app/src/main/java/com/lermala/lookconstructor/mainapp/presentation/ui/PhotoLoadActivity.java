package com.lermala.lookconstructor.mainapp.presentation.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.dsphotoeditor.sdk.activity.DsPhotoEditorActivity;
import com.dsphotoeditor.sdk.utils.DsPhotoEditorConstants;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.lermala.lookconstructor.R;
import com.lermala.lookconstructor.mainapp.presentation.presenters.MainPresenter;

import java.io.ByteArrayOutputStream;

public class PhotoLoadActivity extends AppCompatActivity {
    MainPresenter presenter;

    private static String TAG = "MY_FB_STORAGE";
    public static final int REQUEST_CODE_LOAD_IMAGE_TO_FB = 101;
    public static final int REQUEST_CODE_LOAD_IMAGE_TO_PRESENTATION = 102;

    Button btnChooseImage;
    ImageView imageView;
    ImageButton btnBack;

    private int request;
    private String idPortfolio;

    private static StorageReference mStorageRef;

    private Uri imageUriLocal; // on device drive
    private Uri uploadUri; // ing firebase

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        presenter = new MainPresenter();

        mStorageRef = FirebaseStorage.getInstance().getReference("ImageDB"); // todo
        getData2();

        init();
    }

    private void init(){
        btnChooseImage = findViewById(R.id.btn_choose_img);
        btnBack = findViewById(R.id.btn_back);
        imageView = findViewById(R.id.img_choosen);

        btnChooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkPermisson();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhotoLoadActivity.super.onBackPressed();
            }
        });
    }

    private void checkPermisson() {
        // initialize permisson
        int permission = ActivityCompat.checkSelfPermission(PhotoLoadActivity.this, // todo
                Manifest.permission.WRITE_EXTERNAL_STORAGE);

        // check condition
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
            // when device version is greater than equal to vers 10
            pickImage();
        } else {
            // when device version is below version 10
            // check
            if (permission != PackageManager.PERMISSION_GRANTED){
                // when permission is not granted
                // request permission
                ActivityCompat.requestPermissions(PhotoLoadActivity.this,
                        new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        REQUEST_CODE_LOAD_IMAGE_TO_FB);
            } else {
                // when permission is granted
                pickImage();
            }
        }
    }

    private void pickImage() {
        // Init intent
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");

        startActivityForResult(intent, request);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // check condition
        if (requestCode == REQUEST_CODE_LOAD_IMAGE_TO_PRESENTATION
                && grantResults.length > 0
                && grantResults[0]
                == PackageManager.PERMISSION_GRANTED) {
            // when permission is granted
            pickImage();
        } else if (requestCode == REQUEST_CODE_LOAD_IMAGE_TO_FB
                && grantResults.length > 0
                && grantResults[0]
                == PackageManager.PERMISSION_GRANTED){
            // when permission is granted
            pickImage();
        } else {
            // when permission is denied
            Toast.makeText(PhotoLoadActivity.this.getApplicationContext(), // todo
                    "Отказано в доступе", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            imageUriLocal = data.getData();

            switch (requestCode){
                case REQUEST_CODE_LOAD_IMAGE_TO_FB:
                    Toast.makeText(getApplicationContext(), "REQUEST_CODE_LOAD_IMAGE_TO_FB", Toast.LENGTH_SHORT).show();
                    uploadImage();
                    break;
                case REQUEST_CODE_LOAD_IMAGE_TO_PRESENTATION:
                    imageView.setImageURI(imageUriLocal);
                    loadPresentationActivity(imageUriLocal);

                    // loadPresentationActivity(imageUri); // todo
                    // after editing photo // todo

                    // imgPres.setImageURI(uri);
                    // btPick.setVisibility(View.GONE); // todo
                    // // btSave.setVisibility(View.VISIBLE); // todo
                    Toast.makeText(getApplicationContext(), "REQUEST_CODE_LOAD_IMAGE_TO_PRESENTATION", Toast.LENGTH_SHORT).show();
                    break;

            }
        }
    }

    private void loadPresentationActivity(Uri uri){
        Intent intent = new Intent(PhotoLoadActivity.this,
                DsPhotoEditorActivity.class);
        intent.setData(uri);
        //set data
        intent.setData(uri);
        // set output dir name
        intent.putExtra(DsPhotoEditorConstants.DS_PHOTO_EDITOR_OUTPUT_DIRECTORY,
                "Images");
        // set toolbar color
        intent.putExtra(DsPhotoEditorConstants.DS_TOOL_BAR_BACKGROUND_COLOR,
                Color.parseColor("#FF000000"));
        intent.putExtra(DsPhotoEditorConstants.DS_MAIN_BACKGROUND_COLOR,
                Color.parseColor("#FFFFFF"));
        intent.putExtra(DsPhotoEditorConstants.DS_PHOTO_EDITOR_TOOLS_TO_HIDE,
                new int[]{DsPhotoEditorActivity.TOOL_WARMTH,
                        DsPhotoEditorActivity.TOOL_PIXELATE});
        startActivityForResult(intent, 101);
    }

    private void getData2(){
        Intent intent = getIntent();
        idPortfolio = intent.getStringExtra(Constant.PORTFOLIO_ID);
        request = intent.getIntExtra(Constant.REQUEST_CODE, 0);
    }

    public void getImage(){
        Intent intentChooser = new Intent();
        intentChooser.setType("image/*");
        intentChooser.setAction(Intent.ACTION_GET_CONTENT);

        startActivityForResult(intentChooser, 1);
    }

    private void uploadImage(){
        // reading image
        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] byteArray = baos.toByteArray();

        final StorageReference myRef = mStorageRef.child(System.currentTimeMillis() + "img");

        UploadTask up = myRef.putBytes(byteArray);
        Task<Uri> task = up.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                return myRef.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                Log.d(TAG, "onComlpete started ");
                uploadUri = task.getResult();
                Log.d(TAG, "upload uri=" + uploadUri.toString());
                Log.d(TAG, "task.getResult()" + task.getResult());

                presenter.addPhotoItem(idPortfolio, uploadUri.toString());
                Toast.makeText(getApplicationContext(), "Изображение загружено.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addToDb(){
        // adding to db
        Log.d("PhotoActivity", "start creating portfolioItem");
        // PortfolioItem portfolioItem = new PortfolioItem(WorkWithFirebaseStorage.getUploadUri().toString());
        // PortfolioItem portfolioItem = new PortfolioItem(uploadUri.toString());
        // WorkWithFirebase.addPortfolioItem(idPortfolio, portfolioItem);

    }


    protected void lastonActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1
                && data != null
                && data.getData() != null){
            if (resultCode == RESULT_OK){
                Log.d("MyLog", "Image URI : " + data.getData());
                // imageView.setImageURI(data.getData());
                // uploadImage();
            }
        }
    }

}