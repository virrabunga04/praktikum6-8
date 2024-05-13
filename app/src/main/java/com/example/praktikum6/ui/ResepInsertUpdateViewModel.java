package com.example.praktikum6.ui;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.praktikum6.database.Resep;
import com.example.praktikum6.repository.ResepRepository;

import java.util.List;

public class ResepInsertUpdateViewModel extends ViewModel {
    private ResepRepository ResepDaos;
    public ResepInsertUpdateViewModel(Application application){
        ResepDaos = new ResepRepository(application);
    }
    public LiveData<List<Resep>> getAllReseps(){
        return ResepDaos.getAllReseps();
    }
    public void insert(final Resep resep){
        ResepDaos.insert(resep);
    }
    public void delete(final Resep resep){
        ResepDaos.delete(resep);
    }
    public void update(final Resep resep){
        ResepDaos.update(resep);
    }
}
