package com.example.cartoon;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Cartoon.class}, version = 1, exportSchema = false)
public abstract class CartoonsDatabase extends RoomDatabase {

    private static final String DB_NAME = "cartoon.db";
    private static CartoonsDatabase instance = null;
    public static CartoonsDatabase getInstance(Application application) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                    application,
                    CartoonsDatabase.class,
                    DB_NAME
            ).build();
        }
        return instance;
    }

    abstract CartoonDao cartoonDao();
}
