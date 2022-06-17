package com.lermala.lookconstructor.mainapp.domain.repository;

import com.google.firebase.database.Query;
import com.lermala.lookconstructor.mainapp.data.models.Portfolio;

public interface UserRepository {
    void addPortfolioWithName(String name);
    void deletePortfolio(String idPortfolio);
    void addPortfolio(Portfolio portfolio);
    void editPortfolio(String idPortfolio, String newName);
    Query getPortfolios();

    void addPortfolioItem(String portfolioId, String photoUri);
    void deletePortfolioItem(String idPortfolio, String ItemId);
    Query getPortfolioItems(String porfolioId);

    Query getUserInfo();

    void editUserInfo(String newName, String newBDate);
}
