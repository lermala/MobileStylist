package com.lermala.lookconstructor.mainapp.domain.repository;

public interface AuthRepository {
    boolean signUp(String login, String password);
    boolean signIn(String login, String password);
    boolean isAuthorized();
    void signOut();
    String getUid();
}
