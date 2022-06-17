package com.lermala.lookconstructor.mainapp.data.models;

import java.util.Map;

public class User {
    public String id, name, b_date, email, image_id;

    // public List<Portfolio> portfolios;
    public Map<String, Portfolio> portfolios;

    public User() {
    }

    public User(String id, String name, String b_date, String email, String image_id, Map<String, Portfolio> portfolios) {
        this.id = id;
        this.name = name;
        this.b_date = b_date;
        this.email = email;
        this.image_id = image_id;
        this.portfolios = portfolios;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getB_date() {
        return b_date;
    }

    public String getEmail() {
        return email;
    }

    public String getImage_id() {
        return image_id;
    }

    public Map<String, Portfolio> getPortfolios() {
        return portfolios;
    }

    /*public User(String id, String name, String secName, String email, String image_id) {
        this.id = id;
        this.name = name;
        this.secName = secName;
        this.email = email;
        this.image_id = image_id;
    }*/
}
