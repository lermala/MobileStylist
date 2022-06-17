package com.lermala.lookconstructor.mainapp.domain.usecases.auth;

import com.lermala.lookconstructor.mainapp.domain.repository.AuthRepository;

public final class SignUpUser {
    private final AuthRepository repository;
    private final String login;
    private final String password;

    public SignUpUser(AuthRepository repository, String login, String password) {
        this.repository = repository;
        this.login = login;
        this.password = password;
    }

    public boolean signUpByEmail(){
        return repository.signUp(login, password);
    }
}
