package com.example.sandy.recordstore.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = Artist.class,
        parentColumns = "id",
        childColumns = "artistId",
        onDelete = CASCADE))
public class Album implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "artistId")
    private int artistId;

    @ColumnInfo(name = "quantity")
    private int quantity;

    @Ignore
    private Artist artist;

    public Album() {

    }

    public Album(String title, int artistId, int quantity) {
        this.title = title;
        this.artistId = artistId;
        this.quantity = quantity;
    }

//    public Album(String title, Artist artist, int quantity) {
//        this.title = title;
//        this.artist = artist;
//        this.quantity = quantity;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getArtistId() {
        return this.artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Artist getArtist() {
        return this.artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String stockLevel() {
        if (quantity >= 10) {
            return "High";
        } else if (quantity >= 5) {
            return "Medium";
        } else {
            return "Low";
        }
    }
}

