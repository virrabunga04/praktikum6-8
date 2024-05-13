package com.example.praktikum6.ui;

import android.app.Application;
import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.praktikum6.database.Resep;
import com.example.praktikum6.repository.ResepRepository;

import java.util.List;

public class ResepMainViewModel extends ViewModel {
    private final ResepRepository rResepRepository;
    public ResepMainViewModel(Application application){
        rResepRepository = new ResepRepository(application);
    }
    LiveData<List<Resep>> getAllReseps(){
        return rResepRepository.getAllReseps();
    }
}
