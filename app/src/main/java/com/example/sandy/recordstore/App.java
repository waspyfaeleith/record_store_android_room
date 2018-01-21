package com.example.sandy.recordstore;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.example.sandy.recordstore.models.Album;
import com.example.sandy.recordstore.models.Artist;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sandy on 21/01/2018.
 */

public class App extends Application {

    public static App INSTANCE;
    private static final String DATABASE_NAME = "RecordStore";
    private static final String PREFERENCES = "RoomDemo.preferences";
    private static final String KEY_FORCE_UPDATE = "force_update";

    private AppDatabase database;

    public static App get() {
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        this.database = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, DATABASE_NAME).build();

        INSTANCE = this;
    }

    public AppDatabase getDB() {
        return this.database;
    }

}
