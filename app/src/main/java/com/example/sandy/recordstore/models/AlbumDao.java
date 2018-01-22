package com.example.sandy.recordstore.models;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface AlbumDao {
    @Query("SELECT * FROM album")
    List<Album> getAll();

    @Query("SELECT * FROM album where id = :id")
    Album getById(int id);

    @Insert
    void insert(Album album);

    @Update
    void update(Album album);

    @Delete
    void delete(Album album);

    @Insert
    void deleteAll(Album album);

    @Query("SELECT * FROM artist WHERE id = :artistId")
    Artist getArtist(int artistId);
}
