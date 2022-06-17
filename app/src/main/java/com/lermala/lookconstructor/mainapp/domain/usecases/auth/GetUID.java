package com.lermala.lookconstructor.mainapp.domain.usecases.auth;

import com.lermala.lookconstructor.mainapp.domain.repository.AuthRepository;

public final class GetUID {
    private final AuthRepository repository;

    public GetUID(AuthRepository repository) {
        this.repository = repository;
    }

    public String getUid(){
        return repository.getUid();
    }
}
