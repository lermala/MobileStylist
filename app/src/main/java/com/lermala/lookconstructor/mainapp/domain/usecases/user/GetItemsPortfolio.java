package com.lermala.lookconstructor.mainapp.domain.usecases.user;

import com.google.firebase.database.Query;
import com.lermala.lookconstructor.mainapp.domain.repository.UserRepository;

public final class GetItemsPortfolio {
    private final UserRepository repository;

    public GetItemsPortfolio(UserRepository repository) {
        this.repository = repository;
    }

    public Query getAllFor(String portfolioId){
        return repository.getPortfolioItems(portfolioId);
    }
}
