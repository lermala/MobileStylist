package com.lermala.lookconstructor.mainapp.domain.usecases.user;

import com.lermala.lookconstructor.mainapp.domain.repository.UserRepository;

public final class AddItemPortfolio {
    private final UserRepository repository;

    public AddItemPortfolio(UserRepository repository) {
        this.repository = repository;
    }

    public void addItemPortfolio(String idPotfolio, String photoUri){
        repository.addPortfolioItem(idPotfolio, photoUri);
    }
}
