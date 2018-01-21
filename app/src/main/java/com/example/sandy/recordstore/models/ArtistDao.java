package com.example.sandy.recordstore.models;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sandy on 21/01/2018.
 */

@Dao
public interface ArtistDao {
    @Query("SELECT * FROM artist")
    List<Artist> getAll();

    @Query("SELECT * FROM artist where id = :id")
    Artist getById(int id);

    @Insert
    void insert(Artist artist);

    @Update
    void update(Artist artist);

    @Delete
    void delete(Artist artist);

    @Insert
    void deleteAll(Artist... artists);
}
