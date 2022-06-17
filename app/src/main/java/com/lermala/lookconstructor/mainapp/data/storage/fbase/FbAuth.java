package com.lermala.lookconstructor.mainapp.data.storage.fbase;
import android.util.Log;
import androidx.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FbAuth {
    private FirebaseAuth mAuth;
    private FirebaseUser curUser; // информ об авторизации, какой сейчас пользователь
    private DatabaseReference usersRef;

    private String TAG = "FbAuth";

    public FbAuth(){
        mAuth = FirebaseAuth.getInstance();
        updateUsers(); // getting table of users
        updateCurrentUser();
    }

    public void updateUsers() {
        usersRef = FirebaseDatabase.getInstance()
                .getReference(ConstantWorkWithFirebase.USER_KEY);
    }

    public void updateCurrentUser() {
        curUser = mAuth.getCurrentUser();
    }

    public boolean signUp(String login, String password) {
        final boolean[] res = {false};
        mAuth.createUserWithEmailAndPassword(login, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // res[0] = true;
                            res[0] = sendEmailVerification();
                            Log.d(TAG, "user signed up " + res[0]);
                        } else {
                            res[0] = false;
                            Log.d(TAG, "user didn't sign up " + res[0]);
                        }
                    }
                });
        return res[0];
    }

    public boolean signIn(String login, String password) {
        final boolean[] result = {true};
        mAuth.signInWithEmailAndPassword(login, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            result[0] = true;
                            Log.d(TAG, "user signed in " + result[0]);
                        } else {
                            result[0] = false;
                            Log.d(TAG, "user didn't sign in " + result[0]);
                        }
                    }
                });

        return result[0];
    }

    public void changeUserData(String name, String date) {
        usersRef.child(curUser.getUid())
                .child(ConstantWorkWithFirebase.USER_NAME)
                .setValue(name);

        usersRef.child(curUser.getUid())
                .child(ConstantWorkWithFirebase.USER_DATE)
                .setValue(date);
    }

    public void signOut() {
        FirebaseAuth.getInstance().signOut();
        Log.d(TAG, "user signed out");
    }

    public FirebaseUser getCurUser() {
        return curUser;
    }


    public String getUid(){
        return mAuth.getUid();
    }

    // TODO: несуществующий адрес почты
    private boolean sendEmailVerification() {
        final boolean[] res = new boolean[1];
        curUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    res[0] = true;
                    Log.d(TAG, "verification email was sent");
                } else {
                    res[0] = false;
                    Log.d(TAG, "verification email wasn't sent");
                }
            }
        });
        return res[0];
    }


    public FirebaseAuth getmAuth() {
        return mAuth;
    }
}
