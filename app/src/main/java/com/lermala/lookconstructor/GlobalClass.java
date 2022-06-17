package com.lermala.lookconstructor;

import android.app.Application;

import com.lermala.lookconstructor.mainapp.data.repository.AuthRepositoryImpl;
import com.lermala.lookconstructor.mainapp.data.repository.UserRepositoryImpl;
import com.lermala.lookconstructor.mainapp.domain.usecases.AuthCases;
import com.lermala.lookconstructor.mainapp.domain.usecases.UserCases;
import com.lermala.lookconstructor.mainapp.domain.repository.AuthRepository;
import com.lermala.lookconstructor.mainapp.domain.repository.UserRepository;

public class GlobalClass extends Application {
    public static AuthRepository authRepository ;
    public static String uid;
    public static UserRepository userRepository ;

    public static AuthCases authCases ;
    public static UserCases userCases ;

    public static void start(){
        authRepository = new AuthRepositoryImpl();
        uid = ((AuthRepositoryImpl) authRepository).getStringUid();
        userRepository = new UserRepositoryImpl(uid);

        authCases = new AuthCases(authRepository);
        userCases = new UserCases(userRepository);
    }

    public static AuthRepository getAuthRepository() {
        return authRepository;
    }

    public static String getUid() {
        return uid;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public AuthCases getAuthCases() {
        return authCases;
    }

    public UserCases getUserCases() {
        return userCases;
    }
}
