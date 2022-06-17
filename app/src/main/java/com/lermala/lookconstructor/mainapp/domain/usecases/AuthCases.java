package com.lermala.lookconstructor.mainapp.domain.usecases;

import com.lermala.lookconstructor.mainapp.domain.repository.AuthRepository;
import com.lermala.lookconstructor.mainapp.domain.usecases.auth.GetUID;
import com.lermala.lookconstructor.mainapp.domain.usecases.auth.IsAuthorized;
import com.lermala.lookconstructor.mainapp.domain.usecases.auth.SignInUser;
import com.lermala.lookconstructor.mainapp.domain.usecases.auth.SignOutUser;
import com.lermala.lookconstructor.mainapp.domain.usecases.auth.SignUpUser;

public class AuthCases {
    // starts with the app
    // This config initializes the use cases with relevant adapters.
    // If you wanted to change the implementation you could easily switch
    // from one adapter implementation to another without having to modify the use-case code.
    private final AuthRepository authRepository;

    public AuthCases(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public SignUpUser signUpUser(String login, String password){
        return new SignUpUser(authRepository, login, password);
    }

    public SignInUser signInUser(String login, String password){
        return new SignInUser(authRepository, login, password);
    }

    public SignOutUser signOutUser(){
        return new SignOutUser(authRepository);
    }

    public IsAuthorized isAuthorized(){
        return new IsAuthorized(authRepository);
    }

    public GetUID getUID(){
        return new GetUID(authRepository);
    }
}
