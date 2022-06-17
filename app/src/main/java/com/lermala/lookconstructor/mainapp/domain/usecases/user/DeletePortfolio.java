package com.lermala.lookconstructor.mainapp.domain.usecases.user;

import com.lermala.lookconstructor.mainapp.domain.repository.UserRepository;

public final class DeletePortfolio {
    private final UserRepository repository;

    public DeletePortfolio(UserRepository repository) {
        this.repository = repository;
    }

    public void deletePortfolio(String idPortfolio){
        repository.deletePortfolio(idPortfolio);
    }
}
