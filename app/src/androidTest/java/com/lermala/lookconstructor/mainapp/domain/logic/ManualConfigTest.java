package com.lermala.lookconstructor.mainapp.domain.logic;

import com.google.firebase.database.Query;
import com.lermala.lookconstructor.GlobalClass;
import com.lermala.lookconstructor.mainapp.domain.usecases.AuthCases;
import com.lermala.lookconstructor.mainapp.domain.usecases.UserCases;

import junit.framework.TestCase;

import org.junit.BeforeClass;
import org.junit.Test;

public class ManualConfigTest extends TestCase {

    @Test
    public void testStartAuth() {
        GlobalClass.start();
        AuthCases authCases = GlobalClass.authCases;

        String login = "kolodina.lera322@mail.ru";
        String password = "123456";

        boolean result = authCases.signUpUser(login, password).signUpByEmail();
        System.out.println(result);
    }

}