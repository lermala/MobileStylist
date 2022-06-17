package com.lermala.lookconstructor.mainapp.presentation.presenters;

public class LoginPresenter extends BasePresenter {

    public void initData() {
        // GlobalClass.startAuth();
        // GlobalClass.startData();
        // start();
    }

    public boolean checkAuth(){
        return authCases.isAuthorized().IsAuthorized();
    }

    public boolean signUp(String login, String password){
        return authCases.signUpUser(login, password).signUpByEmail();
    }

    public boolean signIn(String login, String password) {
        return authCases.signInUser(login, password).signInWithEmail();
    }
}
