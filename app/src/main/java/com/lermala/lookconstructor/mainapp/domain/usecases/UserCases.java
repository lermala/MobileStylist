package com.lermala.lookconstructor.mainapp.domain.usecases;
import com.lermala.lookconstructor.mainapp.domain.repository.UserRepository;
import com.lermala.lookconstructor.mainapp.domain.usecases.user.AddItemPortfolio;
import com.lermala.lookconstructor.mainapp.domain.usecases.user.AddPortfolio;
import com.lermala.lookconstructor.mainapp.domain.usecases.user.DeletePortfolio;
import com.lermala.lookconstructor.mainapp.domain.usecases.user.DeletePortfolioItem;
import com.lermala.lookconstructor.mainapp.domain.usecases.user.EditPortfolio;
import com.lermala.lookconstructor.mainapp.domain.usecases.user.EditUserInfo;
import com.lermala.lookconstructor.mainapp.domain.usecases.user.GetItemsPortfolio;
import com.lermala.lookconstructor.mainapp.domain.usecases.user.GetPortfolio;
import com.lermala.lookconstructor.mainapp.domain.usecases.user.GetUserInfo;

public class UserCases {
    private final UserRepository userRepository;

    public UserCases(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // all use cases
    public GetPortfolio getPortfolios(){
        return new GetPortfolio(userRepository);
    }

    public AddItemPortfolio addItemPortfolio(){
        return new AddItemPortfolio(userRepository);
    }

    public AddPortfolio addPortfolio(){
        return new AddPortfolio(userRepository);
    }


    public GetItemsPortfolio getItemsPortfolio(){
        return new GetItemsPortfolio(userRepository);
    }

    public DeletePortfolio deletePortfolio(){
        return new DeletePortfolio(userRepository);
    }

    public DeletePortfolioItem deletePortfolioItem(){
        return new DeletePortfolioItem(userRepository);
    }

    public EditPortfolio editPortfolio(){
        return new EditPortfolio(userRepository);
    }

    public GetUserInfo getUserInfo(){ return new GetUserInfo(userRepository);}

    public EditUserInfo editUserInfo(){
        return new EditUserInfo(userRepository);
    }
}
