package com.lermala.lookconstructor.mainapp.domain.usecases.auth;

import com.lermala.lookconstructor.mainapp.domain.repository.AuthRepository;

public final class SignInUser {
    private final AuthRepository repository;
    private final String login;
    private final String password;

    public SignInUser(AuthRepository repository, String login, String password) {
        this.repository = repository;
        this.login = login;
        this.password = password;
    }

    public boolean signInWithEmail(){
        return repository.signIn(login, password);
    }
}
