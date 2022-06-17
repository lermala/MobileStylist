package com.lermala.lookconstructor.mainapp.presentation.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lermala.lookconstructor.GlobalClass;
import com.lermala.lookconstructor.R;
import com.lermala.lookconstructor.mainapp.presentation.presenters.LoginPresenter;

public class LoginActivity extends AppCompatActivity {

    private EditText edLogin, edPassword;
    private Button btnRegister, btnLogIn;
    private TextView tvUserEmail;

    private String TAG = "LoginActivity";

    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // WorkWithFirebase.init(); // TODO: убрать в самое начало работы приложения!!!
        // UserUseCase.initData();
        // todo
        GlobalClass.start();
        presenter = new LoginPresenter();
        // presenter.initData();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login); // todo попробуй постаивть ниже

        // FirebaseUser curUser = WorkWithFirebase.getCurUser();
        if (presenter.checkAuth()){ // если авторизован
            openMainActivity();
            // showSigned();
            // String userName = "Вы вошли как: " + cUser.getEmail();
            // tvUserEmail.setText(userName);
            // Toast.makeText(this, "User not null", Toast.LENGTH_SHORT).show();
        } else { // не авторизован
            // Toast.makeText(this, "User null", Toast.LENGTH_SHORT).show();
            init();
        }
    }

    private void init(){
        btnRegister = findViewById(R.id.btnRegister);
        btnLogIn = findViewById(R.id.btnLogIn);
        edLogin = findViewById(R.id.edLogin);
        edPassword = findViewById(R.id.edPassword);
    }

    public void openMainActivity(){
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }


    public  void onClickSignIn(View view){
        if (!TextUtils.isEmpty(edLogin.getText().toString())
                && !TextUtils.isEmpty(edPassword.getText().toString())){

            // boolean resOfSigning = WorkWithFirebase.signIn(edLogin.getText().toString(), edPassword.getText().toString());
            //WorkWithFirebase.updateCurrentUser();

            boolean resOfSigning = presenter.signIn(edLogin.getText().toString(), edPassword.getText().toString());
                //    UserUseCase.tryToSignInWith(edLogin.getText().toString(), edPassword.getText().toString());
            // Toast.makeText(getApplicationContext(), "resOfSigning= " + resOfSigning, Toast.LENGTH_SHORT).show();

            if(resOfSigning){
                openMainActivity();
            } else {
                Toast.makeText(getApplicationContext(), "Перезайдите в приложение ", Toast.LENGTH_SHORT).show();
            }


//            if (WorkWithFirebase.getCurUser() != null && resOfSigning){
//                Toast.makeText(getApplicationContext(), "hi" + resOfSigning, Toast.LENGTH_SHORT).show();
//                // todo fix if-else
//                if (WorkWithFirebase.getCurUser().isEmailVerified()){ // если верифицирован
//                    openMainActivity(); // пускаем работать в приложение
//                }
//                else { // НЕ верифицирован
//                    Toast.makeText(getApplicationContext(),
//                            "Check your email for verification. email: ",
//                            Toast.LENGTH_SHORT).show();
//                }
//                openMainActivity(); // пускаем работать в приложение
//            } else { // НЕ верифицирован
//                Toast.makeText(getApplicationContext(),
//                        "Перезайдите в приложение ",
//                        Toast.LENGTH_SHORT).show();
//            }

        } else {
            showMessage("Введите логин и пароль");
        }
    }

    public void onClickRegister(View view){
        if (!TextUtils.isEmpty(edLogin.getText().toString())
                && !TextUtils.isEmpty(edPassword.getText().toString())){

            // boolean result = WorkWithFirebase.signUp(edLogin.getText().toString(), edPassword.getText().toString());
            boolean result = presenter.signUp(edLogin.getText().toString(), edPassword.getText().toString());

            // WorkWithFirebase.updateCurrentUser();
            // WorkWithFirebase.updateUsers();

            if (result) {
                openMainActivity();
            } else {
                showMessage("Введите корректные данные");
            }

        } else {
            showMessage("Введите логин и пароль");
        }

    }

    public void showMessage(String msg){
        Toast.makeText(getApplicationContext(),
                msg,
                Toast.LENGTH_SHORT).show();
    }

}