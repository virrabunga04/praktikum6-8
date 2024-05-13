package com.example.praktikum6.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.praktikum6.database.Resep;
import com.example.praktikum6.database.ResepDao;
import com.example.praktikum6.database.ResepRoomDB;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ResepRepository {
    private final ResepDao ResepDaos;
    private final ExecutorService executorService;

    public ResepRepository(Application application) {
        executorService = Executors.newSingleThreadExecutor();
        ResepRoomDB db = ResepRoomDB.getDatabase(application);
        ResepDaos = db.resepDao();
    }

    public LiveData<List<Resep>> getAllReseps() {
        return ResepDaos.getAllReseps();
    }

    public void insert(final Resep resep) {
        executorService.execute(() -> ResepDaos.insert(resep));
    }

    public void delete(final Resep resep) {
        executorService.execute(() -> ResepDaos.delete(resep));
    }

    public void update(final Resep resep) {
        executorService.execute(() -> ResepDaos.update(resep));
    }
}
