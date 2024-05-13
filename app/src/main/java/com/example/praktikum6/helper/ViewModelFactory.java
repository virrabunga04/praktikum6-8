package com.example.praktikum6.helper;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.praktikum6.ui.ResepInsertUpdateViewModel;
import com.example.praktikum6.ui.ResepMainViewModel;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private static volatile ViewModelFactory INSTANCE;
    private final Application rApplication;
    private ViewModelFactory(Application application){
        rApplication = application;
    }
    public static ViewModelFactory getInstance(Application application){
        if (INSTANCE == null){
            synchronized (ViewModelFactory.class){
                INSTANCE = new ViewModelFactory(application);
            }
        }
        return INSTANCE;
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass){
        if (modelClass.isAssignableFrom(ResepMainViewModel.class)) {
            return (T) new ResepMainViewModel(rApplication);
        }else if(modelClass.isAssignableFrom(ResepInsertUpdateViewModel.class)){
            return (T) new ResepInsertUpdateViewModel(rApplication);
        }
        throw new IllegalArgumentException("Unknown ViewModel class: "+ modelClass.getName());
    }
}
