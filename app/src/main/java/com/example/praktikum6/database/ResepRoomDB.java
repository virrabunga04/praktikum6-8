package com.example.praktikum6.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Resep.class}, version = 1)
public abstract class ResepRoomDB extends RoomDatabase {
    public abstract ResepDao resepDao();
    private static volatile ResepRoomDB INSTANCE;
    public static ResepRoomDB getDatabase(final Context context){
        if (INSTANCE == null){
            synchronized (ResepRoomDB.class){
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(), ResepRoomDB.class, "resep_db").build();
            }
        }
        return INSTANCE;
    }
}
