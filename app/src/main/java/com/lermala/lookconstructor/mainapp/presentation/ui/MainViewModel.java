package com.lermala.lookconstructor.mainapp.presentation.ui;

import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    // View - все, что относится к интерфейсу
    // ViewModel - логика
    // нажатие кнопки делегируется во ViewModel
    // во ViewModel не должно быть контекста!!!

    public MainViewModel() {
    }

    private void init(){

    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
