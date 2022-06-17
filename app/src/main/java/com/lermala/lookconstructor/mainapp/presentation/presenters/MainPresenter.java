package com.lermala.lookconstructor.mainapp.presentation.presenters;

import com.google.firebase.database.Query;

public class MainPresenter extends BasePresenter{

    public void addPortfolioWithName(String name){
        userCases.addPortfolio().addPortfolioWithName(name);
    }

    public void editPortfolio(String portfolioId, String name) {
        userCases.editPortfolio().editPortfolio(portfolioId, name);
    }

    public void deletePortfolio(String contextMenuClickedItemListId) {
        userCases.deletePortfolio().deletePortfolio(contextMenuClickedItemListId);
    }

    public Query getAllPortfolios(){
        Query query = userCases.getPortfolios().getPortfolios();
        return query;
    }

    public Query getAllItemsFor(String idPortfolio) {
        return userCases.getItemsPortfolio().getAllFor(idPortfolio);
    }

    public void deleteItem(String idPortfolio, String imageId) {
        userCases.deletePortfolioItem().deletePortfolioItem(idPortfolio, imageId);
    }

    public void addPhotoItem(String idPortfolio, String imageUri) {
        userCases.addItemPortfolio().addItemPortfolio(idPortfolio, imageUri);
    }

    public Query getUserData() {
        return userCases.getUserInfo().getUserInfo();
    }

    public void signOut() {
        authCases.signOutUser().signOut();
    }

    public void changeUserData(String name, String date) {
        userCases.editPortfolio().editPortfolio(name, date);
    }
}
