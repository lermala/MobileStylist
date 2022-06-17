package com.lermala.lookconstructor.mainapp.data.storage.fbase;

import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

public class WorkWithFirebaseStorage {
    private static String TAG = "MY_FB_STORAGE";

    private static StorageReference mStorageRef;
    // private static Uri uploadUri ;
    private static Uri uploadUri
            = Uri.parse("https://firebasestorage.googleapis.com/v0/b/testdb-d13ea.appspot.com/o/ImageDB%2F2e40ce76d4626241838f5200ee71ede6.jpg?alt=media&token=4ecf4a38-f942-40a5-880f-ad0ca467a5a9");

    public static void init(){
        mStorageRef = FirebaseStorage.getInstance().getReference("ImageDB");
    }

    public static boolean uploadImage(Bitmap bitmap){
        Log.d(TAG, "start uploading image");
        final boolean[] result = {false};

        // Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
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
                    result[0] = true;
                    // saveUser();
                    // Toast.makeText(getApplicationContext(), "Картинка успешно загружена", Toast.LENGTH_SHORT).show();
                }
            });

        return result[0];
    }

    public static Uri getUploadUri() {
        Log.d(TAG, "getUploadUri=" + uploadUri);
        return uploadUri;
    }

}
