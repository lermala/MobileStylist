package com.lermala.lookconstructor.mainapp.domain.usecases.user;

import com.lermala.lookconstructor.mainapp.domain.repository.UserRepository;

public final class EditPortfolio {
    private final UserRepository repository;

    public EditPortfolio(UserRepository repository) {
        this.repository = repository;
    }

    public void editPortfolio(String idPortfolio, String newName){
        repository.editPortfolio(idPortfolio, newName);
    }
}
