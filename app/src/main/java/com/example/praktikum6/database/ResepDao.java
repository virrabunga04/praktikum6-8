package com.example.praktikum6.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ResepDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Resep resep);
    @Update()
    void update(Resep resep);
    @Delete()
    void delete(Resep resep);
    @Query("SELECT * from resep ORDER BY id ASC")
    LiveData<List<Resep>> getAllReseps();
}
