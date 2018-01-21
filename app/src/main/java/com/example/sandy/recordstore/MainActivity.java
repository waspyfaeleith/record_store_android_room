package com.example.sandy.recordstore;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.sandy.recordstore.models.Album;
import com.example.sandy.recordstore.models.Artist;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Album> albums;
    List<Artist> artists;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(this.getClass().toString(), "ON CREATE CALLED");
        this.seedData();
    }

    private void seedData() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                App.get().getDB().artistDao().deleteAll();
                Artist artist1 = new Artist("AC/DC");
                App.get().getDB().artistDao().insert(artist1);
                Artist artist2 = new Artist("Iron Maiden");
                App.get().getDB().artistDao().insert(artist2);
            }
        }).start();

    }

    public void onClickArtists(View view) {
        Intent intent = new Intent(MainActivity.this, ArtistsActivity.class);
        startActivity(intent);
    }
}
