package com.lermala.lookconstructor.mainapp.presentation.ui.frags.subfrags;

import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dsphotoeditor.sdk.ui.photoview.PhotoView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.lermala.lookconstructor.R;
import com.lermala.lookconstructor.mainapp.presentation.ui.Constant;
import com.orhanobut.logger.Logger;
import com.squareup.picasso.Picasso;

public class DetailedImage extends Fragment {
    String idImage; // id of portfolio that contains current files (images)
    String TAG = "DetailedImage";

    RelativeLayout rl;
    LinearLayout btnsLay;
    ImageButton ic_back;
    TextView topTxt;
    BottomNavigationView bottomNavigationView;

    int black, white;

    private View v;

    public DetailedImage() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_detailed_image, container, false);

        init();
        openDesign();

        idImage = this.getArguments().getString(Constant.PORTFOLIO_ITEM_IMAGE_ID); // взяли ид

        // теперь нужно загрузить то изображение сюда. todo
        // как вариант - брать не ссылку на изображение, а прямо изображение передавать
        PhotoView imageView = v.findViewById(R.id.img_detailed);
        Picasso.get().load(idImage).into(imageView);

        return v;
    }

    private void init(){
        // top toolbar
        rl = getActivity().findViewById(R.id.top_layout);
        ic_back = rl.findViewById(R.id.btn_back);
        topTxt = rl.findViewById(R.id.top_text);
        btnsLay = rl.findViewById(R.id.top_btns_lay);

        // bot menu
        bottomNavigationView =  getActivity().findViewById(R.id.bottomNavigationView);

        // colors
        black = ContextCompat.getColor(getContext(), R.color.black);
        white = ContextCompat.getColor(getContext(), R.color.white);
    }

    private void openDesign(){
        bottomNavigationView.setVisibility(View.GONE); // closing the bot menu
        btnsLay.setVisibility(View.GONE); // default

        topTxt.setText("Фотография");
        topTxt.setTextColor(white);

        ic_back.setColorFilter(white);
        rl.setBackgroundColor(black);
    }

    private void closeDesign(){
        bottomNavigationView.setVisibility(View.VISIBLE); // closing the bot menu

        btnsLay.setVisibility(View.VISIBLE);
        // topTxt.setVisibility(View.VISIBLE);
        topTxt.setText("Мобильный стилист");
        topTxt.setTextColor(black);

        ic_back.setColorFilter(black);
        // rl.setBackgroundColor(white);
        rl.setBackground(getResources().getDrawable(R.drawable.custom_toolbar));
    }

    @Override
    public void onResume() {
        Logger.d(TAG + "onResume()");
        openDesign();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        Logger.d(TAG +"onDestroy()");
        closeDesign();
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        Logger.d(TAG +"onDetach()");
        closeDesign();
        super.onDetach();
    }

    @Override
    public void onStop() {
        Logger.d(TAG +"onStop()");
        closeDesign();
        super.onStop();
    }


}