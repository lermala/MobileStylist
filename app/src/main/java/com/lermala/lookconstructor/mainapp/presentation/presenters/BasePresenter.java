package com.lermala.lookconstructor.mainapp.presentation.presenters;

import com.lermala.lookconstructor.GlobalClass;
import com.lermala.lookconstructor.mainapp.domain.usecases.AuthCases;
import com.lermala.lookconstructor.mainapp.domain.usecases.UserCases;

public class BasePresenter {
    AuthCases authCases = GlobalClass.authCases;
    UserCases userCases = GlobalClass.userCases;


    public void start() {

    }

}
