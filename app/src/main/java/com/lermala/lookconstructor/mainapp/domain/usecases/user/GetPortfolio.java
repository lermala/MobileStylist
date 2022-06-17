package com.lermala.lookconstructor.mainapp.domain.usecases.user;

import com.google.firebase.database.Query;
import com.lermala.lookconstructor.mainapp.domain.repository.UserRepository;

public final class GetPortfolio {
    private final UserRepository repository;

    public GetPortfolio(UserRepository repository) {
        this.repository = repository;
    }

    public Query getPortfolios(){
        return repository.getPortfolios();
    }
}
