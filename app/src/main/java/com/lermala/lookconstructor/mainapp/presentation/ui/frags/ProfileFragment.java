package com.lermala.lookconstructor.mainapp.presentation.ui.frags;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.lermala.lookconstructor.mainapp.data.models.User;
import com.lermala.lookconstructor.mainapp.presentation.presenters.MainPresenter;
import com.lermala.lookconstructor.mainapp.presentation.ui.LoginActivity;
import com.lermala.lookconstructor.R;
import com.lermala.lookconstructor.helpers.ComponentsHelper;

public class ProfileFragment extends Fragment {
    MainPresenter presenter;

    EditText edName, edDate, edEmail;
    Button btnSave, btnExit, btnEdit, btnCancel;

    ComponentsHelper edDateHelper, edNameHelper, edEmailHelper;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        presenter = new MainPresenter();

        init(view);
        design();

        return view;
    }

    private void init(View view){
        edName = view.findViewById(R.id.edit_name);
        edDate = view.findViewById(R.id.edit_date);
        edEmail = view.findViewById(R.id.edit_email);

        btnEdit = view.findViewById(R.id.btn_edit_profile);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickEdit();
            }
        });

        btnSave = view.findViewById(R.id.btn_save_profile);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickSave();
            }
        });

        btnCancel = view.findViewById(R.id.btn_cancel_profile);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickCancel();
            }
        });

        btnExit = view.findViewById(R.id.btn_exit);
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickExit();
            }
        });

        readUser();
    }

    private void design(){
        selectMenuItem();
        showBtns();
    }

    // this need for getting back
    private void selectMenuItem(){
        BottomNavigationView navigation = (BottomNavigationView) getActivity()
                .findViewById(R.id.bottomNavigationView);
        navigation.getMenu().getItem(2).setChecked(true);
    }

    private void readUser(){
       // UserUseCase.writeUserDataTo(edName, edDate, edEmail);
       writeUserData(presenter.getUserData());
    }

    public void writeUserData(Query query){
        // getUserRef.addValueEventListener

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);

                edName.setText(user.getName());
                edDate.setText(user.getB_date());
                edEmail.setText(user.getEmail());

                Log.d("TAG", "init: " + user.getEmail() + " "
                        + user.getB_date() + " " + user.getName());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        };

        query.addValueEventListener(valueEventListener);
    }

    public void onClickEdit(){
        showBtnsForEditing();
    }

    public void onClickCancel(){
        showBtns();
    }

    public void showBtnsForEditing(){
        btnSave.setVisibility(View.VISIBLE);
        btnCancel.setVisibility(View.VISIBLE);
        btnExit.setVisibility(View.GONE);
        btnEdit.setVisibility(View.GONE);

        ableEdTexts();
    }

    public void showBtns(){
        btnSave.setVisibility(View.GONE);
        btnCancel.setVisibility(View.GONE);
        btnExit.setVisibility(View.VISIBLE);
        btnEdit.setVisibility(View.VISIBLE);

        disableEdTexts();
    }

    private void disableEdTexts(){
        edDateHelper = new ComponentsHelper(edDate);
        edNameHelper = new ComponentsHelper(edName);
        edEmailHelper = new ComponentsHelper(edEmail);

        edDateHelper.disableEditText();
        edNameHelper.disableEditText();
        edEmailHelper.disableEditText();
    }

    private void ableEdTexts(){
        edDateHelper.ableEditText();
        edNameHelper.ableEditText();
    }

    public void onClickSave(){
        // read
        String name = edName.getText().toString();
        String date = edDate.getText().toString();

        // check for correct inp
        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(date)){ // if is not empty
            // change user in db
            // ...
            // WorkWithFirebase.changeUserData(name, date);
            // UserUseCase.changeUserData(name, date);
            // writeUserData(presenter.getUserData());
            presenter.changeUserData(name, date);

            showBtns();
            showMessage("Данные обновлены");
        } else {
            showMessage("Заполните поля");
        }

    }

    public void showMessage(String msg){
        Toast.makeText(getContext(),
                msg,
                Toast.LENGTH_SHORT).show();
    }


    public void onClickExit(){
        // UserUseCase.exit();
        presenter.signOut();
        Intent intent = new Intent(getContext(), LoginActivity.class);
        startActivity(intent);
    }
}