package com.lermala.lookconstructor.mainapp.data.models;

import java.util.Map;

public class Portfolio {
    public Map<String, PortfolioItem> portfolio_items; // images=files
    public String name;
    public String id;
    public String image_id;

    public Portfolio() {
    }

    public Portfolio(Map<String, PortfolioItem> portfolio_items, String name) {
        this.portfolio_items = portfolio_items;
        this.name = name;
    }

    public Portfolio(String id, String name) {
        this.name = name;
        this.id = id;
    }

    public Portfolio(Map<String, PortfolioItem> portfolio_items, String name, String id) {
        this.portfolio_items = portfolio_items;
        this.name = name;
        this.id = id;
    }

    public Portfolio(Map<String, PortfolioItem> portfolio_items, String name, String id, String image_id) {
        this.portfolio_items = portfolio_items;
        this.name = name;
        this.id = id;
        this.image_id = image_id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Portfolio(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public Map<String, PortfolioItem> getPortfolio_items() {
        return portfolio_items;
    }

    public void setPortfolio_items(Map<String, PortfolioItem> portfolio_items) {
        this.portfolio_items = portfolio_items;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage_id() {
        return image_id;
    }

    public void setImage_id(String image_id) {
        this.image_id = image_id;
    }
}
