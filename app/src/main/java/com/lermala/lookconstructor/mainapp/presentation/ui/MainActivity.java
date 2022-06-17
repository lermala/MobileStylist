package com.lermala.lookconstructor.mainapp.presentation.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.lermala.lookconstructor.R;
import com.lermala.lookconstructor.databinding.ActivityMainBinding;
import com.lermala.lookconstructor.mainapp.presentation.ui.frags.PresentationFragment;
import com.lermala.lookconstructor.mainapp.presentation.ui.frags.ProfileFragment;
import com.lermala.lookconstructor.mainapp.presentation.ui.frags.PortfoliosListFragment;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class); // todo
        // mainViewModel = ViewModelProvider.
        //ViewModelProvider.AndroidViewModelFactory(MainViewModel);

        // загружаем что-то, чтобы не было пустоты (начальный фрагмент)
        if (savedInstanceState == null){
            fragmentManager = getSupportFragmentManager();
            // FragmentTransaction parentFragMan = fragmentManager.beginTransaction();
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.main_fl, new PresentationFragment())
                    .commit();
        }


        binding.bottomNavigationView.setOnItemSelectedListener(item ->{
            // todo запоминание фрагмента а не создание нового!!!
            switch (item.getItemId()){

                case R.id.portfolioFragment:
                    replaceFragment(new PortfoliosListFragment());
                    break;

                case R.id.presentationFragment:
                    replaceFragment(new PresentationFragment());
                    break;

                case R.id.profileFragment:
                    replaceFragment(new ProfileFragment());
                    break;
            }

            return true;
        });

        init();
    }

    ImageView btnBack, btnBurger;
    private void init(){
        btnBack = findViewById(R.id.btn_back);
        btnBurger = findViewById(R.id.top_burger);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBack();
            }
        });
    }

    private void goBack(){
        // finish();
        // https://ask-dev.ru/info/17365/android-go-back-to-previous-activity
        //super.onBackPressed();

        // FragmentManager fm = this.getSupportFragmentManager();
        // fragmentManager.popBackStack();
        fragmentManager.popBackStack();

        // finish();
    }

    @Override
    public void onBackPressed() {
        // super.onBackPressed();
        goBack();
    }

    FragmentManager fragmentManager;
    public void replaceFragment(Fragment fragment){
        fragmentManager = getSupportFragmentManager();
        // FragmentTransaction parentFragMan = fragmentManager.beginTransaction();
        fragmentManager
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.main_fl, fragment)
                .commit();
    }

}