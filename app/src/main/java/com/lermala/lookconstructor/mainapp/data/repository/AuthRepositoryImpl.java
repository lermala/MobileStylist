package com.lermala.lookconstructor.mainapp.data.repository;

import com.lermala.lookconstructor.mainapp.data.storage.fbase.FbAuth;
import com.lermala.lookconstructor.mainapp.domain.repository.AuthRepository;

public class AuthRepositoryImpl implements AuthRepository {
    FbAuth fbAuth;

    public AuthRepositoryImpl(){
        fbAuth = new FbAuth();
    }

    @Override
    public boolean signUp(String login, String password) {
        return fbAuth.signUp(login, password);
    }

    @Override
    public boolean signIn(String login, String password) {
        return fbAuth.signIn(login, password);
    }

    @Override
    public boolean isAuthorized() {
        if (fbAuth.getUid() != null){ // todo change
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void signOut() {
        fbAuth.signOut();
    }

    @Override
    public String getUid(){
        return fbAuth.getUid();
    }

    public String getStringUid(){
        return fbAuth.getUid();
    }

}
