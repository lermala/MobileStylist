package com.lermala.lookconstructor.mainapp.domain.usecases.auth;

import com.lermala.lookconstructor.mainapp.domain.repository.AuthRepository;

public final class IsAuthorized {
    private final AuthRepository repository;


    public IsAuthorized(AuthRepository repository) {
        this.repository = repository;

    }

    public boolean IsAuthorized(){
        return repository.isAuthorized();
    }
}
