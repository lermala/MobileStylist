package com.lermala.lookconstructor.mainapp.domain.usecases.user;

import com.google.firebase.database.Query;
import com.lermala.lookconstructor.mainapp.domain.repository.UserRepository;

public final class GetUserInfo {
    private final UserRepository repository;

    public GetUserInfo(UserRepository repository) {
        this.repository = repository;
    }

    public Query getUserInfo(){
        return repository.getUserInfo();
    }
}
