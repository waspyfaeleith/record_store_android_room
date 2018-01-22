package com.example.sandy.recordstore;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.sandy.recordstore.models.Album;
import com.example.sandy.recordstore.models.AlbumDao;
import com.example.sandy.recordstore.models.Artist;
import com.example.sandy.recordstore.models.ArtistDao;

/**
 * Created by sandy on 21/01/2018.
 */

@Database(entities = {Artist.class, Album.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ArtistDao artistDao();
    public abstract AlbumDao albumDao();
}
