package com.lermala.lookconstructor.mainapp.domain.usecases.auth;

import com.lermala.lookconstructor.mainapp.domain.repository.AuthRepository;

public final class SignOutUser {
    private final AuthRepository repository;

    public SignOutUser(AuthRepository repository) {
        this.repository = repository;
    }

    public void signOut(){
        repository.signOut();
    }
}
