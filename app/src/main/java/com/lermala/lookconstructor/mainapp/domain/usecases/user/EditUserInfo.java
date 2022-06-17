package com.lermala.lookconstructor.mainapp.domain.usecases.user;

import com.lermala.lookconstructor.mainapp.domain.repository.UserRepository;

public final class EditUserInfo {
    private final UserRepository repository;

    public EditUserInfo(UserRepository repository) {
        this.repository = repository;
    }

    public void editUserInfo(String newName, String newBdate){
        repository.editUserInfo(newName, newBdate);
    }
}
