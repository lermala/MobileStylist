package com.lermala.lookconstructor.mainapp.domain.usecases.user;

import com.lermala.lookconstructor.mainapp.domain.repository.UserRepository;

public final class DeletePortfolioItem {
    private final UserRepository repository;

    public DeletePortfolioItem(UserRepository repository) {
        this.repository = repository;
    }

    public void deletePortfolioItem(String idPortfolio, String itemId){
        repository.deletePortfolioItem(idPortfolio, itemId);
    }
}
