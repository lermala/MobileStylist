package com.lermala.lookconstructor.mainapp.data.repository;
import com.google.firebase.database.Query;
import com.lermala.lookconstructor.mainapp.data.storage.fbase.FbWork;
import com.lermala.lookconstructor.mainapp.data.models.Portfolio;
import com.lermala.lookconstructor.mainapp.domain.repository.UserRepository;

public class UserRepositoryImpl implements UserRepository {
    private FbWork fbWork;

    public UserRepositoryImpl(String uid){
        fbWork = new FbWork(uid);
    }

    @Override
    public void addPortfolio(Portfolio portfolio) {
        fbWork.addPortfolio(portfolio);
    }

    @Override
    public void addPortfolioWithName(String name) {
        fbWork.addPortfolio(name);
    }

    @Override
    public void deletePortfolio(String idPortfolio) {
        fbWork.deletePortfolioFromDB(idPortfolio);
    }

    @Override
    public void editPortfolio(String idPortfolio, String newName) {
        fbWork.editPortfolio(idPortfolio, newName);
    }

    @Override
    public Query getPortfolios() {
        return fbWork.getPortfolios();
    }

    @Override
    public void addPortfolioItem(String portfolioId, String photoUri) {
        // fbWork.addPortfolioItem();
        fbWork.addPortfolioItem(portfolioId, photoUri);
    }

    @Override
    public void deletePortfolioItem(String idPortfolio, String ItemId) {
        fbWork.deletePortfolioItemFromDB(idPortfolio, ItemId);
    }

    @Override
    public Query getPortfolioItems(String porfolioId) {
        return fbWork.getPortfolioItemsForPortfolio(porfolioId);
    }

    @Override
    public Query getUserInfo() {
        return fbWork.getUserInfo();
    }

    @Override
    public void editUserInfo(String newName, String newBDate) {
        fbWork.changeUserData(newName, newBDate);
    }
}
