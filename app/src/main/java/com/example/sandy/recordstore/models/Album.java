package com.example.sandy.recordstore.models;

public class Album {

    private int id;

    private String title;

    private int artistId;

    private int quantity;
    private Artist artist;

    public Album() {

    }

    public Album(String title, int artistID, int quantity) {
        this.title = title;
        this.artistId = artistId;
        this.quantity = quantity;
    }

    public Album(String title, Artist artist, int quantity) {
        this.title = title;
        this.artist = artist;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getArtistId() {
        return artistId;
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

