package com.lermala.lookconstructor.mainapp.presentation.ui.frags;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.lermala.lookconstructor.mainapp.presentation.ui.PhotoLoadActivity;
import com.lermala.lookconstructor.R;
import com.lermala.lookconstructor.helpers.RecyclerViewAdapter;
import com.lermala.lookconstructor.mainapp.data.models.Portfolio;
import com.lermala.lookconstructor.mainapp.presentation.ui.Constant;

import java.util.ArrayList;


public class PresentationFragment extends Fragment {
    Button btnCreate;
    Button btnOpenImg;

    Button btPick;
    Button btSave;
    ImageView imgPres;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_presentation, container, false);

        init(view);
        design();

        return view;
    }

    private void design() {
        selectMenuItem();
    }

    // this need for getting back
    private void selectMenuItem(){
        BottomNavigationView navigation = (BottomNavigationView) getActivity()
                .findViewById(R.id.bottomNavigationView);
        navigation.getMenu().getItem(1).setChecked(true);
    }

    private void init(View view){
        btnCreate = view.findViewById(R.id.pres_btn_create);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // code for creating presentation todo
                Intent intent = new Intent(getActivity(), PhotoLoadActivity.class);
                intent.putExtra(Constant.REQUEST_CODE, PhotoLoadActivity.REQUEST_CODE_LOAD_IMAGE_TO_PRESENTATION);
                startActivity(intent);

            }
        });

        ArrayList<Portfolio> portfolios = new ArrayList<>();
        portfolios.add(new Portfolio("name1"));
        portfolios.add(new Portfolio("name2"));
        portfolios.add(new Portfolio("name3"));
        portfolios.add(new Portfolio("name4"));
        portfolios.add(new Portfolio("name5"));
        portfolios.add(new Portfolio("name6"));
        portfolios.add(new Portfolio("name7"));
        initRecycleView(view, portfolios);
    }

    private void initRecycleView(View view, ArrayList<Portfolio> portfolios){
        // LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),
        //         LinearLayoutManager.HORIZONTAL,
        //         false);
//
        // RecyclerView recyclerView = view.findViewById(R.id.recycle_presentation);
        // recyclerView.setLayoutManager(layoutManager);


        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL,
                false);

        // set up the RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.recycle_presentation);
        RecyclerView recyclerViewPhotos = view.findViewById(R.id.recycle_presentation_photo);
        recyclerView.setLayoutManager(layoutManager);
        // adapter = new MyRecyclerViewAdapter(this, animalNames);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getContext(), portfolios); // todo
        recyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManagerPhotos = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL,
                false);
        recyclerViewPhotos.setLayoutManager(layoutManagerPhotos);
        recyclerViewPhotos.setAdapter(adapter);
    }

    private void initRecycleView2(View view, ArrayList<Portfolio> portfolios){
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL,
                false);

        RecyclerView recyclerView = view.findViewById(R.id.recycle_presentation);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getContext(), portfolios);
        recyclerView.setAdapter(adapter);
    }







}