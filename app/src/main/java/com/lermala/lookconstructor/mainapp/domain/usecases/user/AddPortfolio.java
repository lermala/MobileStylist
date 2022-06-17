package com.lermala.lookconstructor.mainapp.domain.usecases.user;

import com.lermala.lookconstructor.mainapp.domain.repository.UserRepository;
import com.lermala.lookconstructor.mainapp.data.models.Portfolio;

public final class AddPortfolio {
    private final UserRepository repository;

    public AddPortfolio(UserRepository repository) {
        this.repository = repository;
    }

    public void addPortfolio(Portfolio portfolio){
        repository.addPortfolio(portfolio);
    }

    public void addPortfolioWithName(String name){
        repository.addPortfolioWithName(name);
    }
}
