package com.lermala.lookconstructor.helpers;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class ImageWorker extends AppCompatActivity {

    private void pickImage() {
        // Init intent
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 100);
    }

    private void checkPermisson() {
        // initialize permisson
        int permission = ActivityCompat.checkSelfPermission(this, // todo ImageWorker.this
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
                ActivityCompat.requestPermissions(this, // todo
                        new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        100);
            } else {
                // when permission is granted
                pickImage();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // check condition
        if (requestCode == 100 && grantResults.length > 0 && grantResults[0]
                == PackageManager.PERMISSION_GRANTED) {
            // when permission is granted
            pickImage();
        } else {
            // when permission is denied
            Toast.makeText(this.getApplicationContext(), // todo ImageWorker.this
                    "Permission Denied", Toast.LENGTH_SHORT).show();
        }
    }

}
