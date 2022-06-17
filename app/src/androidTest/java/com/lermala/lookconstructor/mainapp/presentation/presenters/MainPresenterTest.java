package com.lermala.lookconstructor.mainapp.presentation.presenters;

import static org.junit.Assert.*;

import com.google.firebase.database.Query;
import com.lermala.lookconstructor.GlobalClass;

import org.junit.Test;

public class MainPresenterTest {

    Query query;

    @Test
    public void getAllPortfolios() {
        // GlobalClass.start();
        System.out.println("global " + GlobalClass.uid);
        MainPresenter mainPresenter = new MainPresenter();
        // BasePresenter basePresenter = new BasePresenter();
        // basePresenter.start();
        // mainPresenter.start();
        // Query query = basePresenter.userCases.getPortfolios().getPortfolios();
        query = mainPresenter.getAllPortfolios();
        // query = userCases.getPortfolios().getPortfolios();

        // System.out.println("query " + query);
        System.out.println("query " + query.toString());

    }

}