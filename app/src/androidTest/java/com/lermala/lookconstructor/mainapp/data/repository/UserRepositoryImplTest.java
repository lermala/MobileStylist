package com.lermala.lookconstructor.mainapp.data.repository;

import com.google.firebase.database.Query;
import com.lermala.lookconstructor.GlobalClass;

import org.junit.Test;

public class UserRepositoryImplTest {

    @Test
    public void getPortfolios() {
        UserRepositoryImpl userRepository = new UserRepositoryImpl(GlobalClass.getUid());
        System.out.println("UID " + GlobalClass.getUid());
        Query query = userRepository.getUserInfo();
        System.out.println("QOERY " + query.toString());


    }
}