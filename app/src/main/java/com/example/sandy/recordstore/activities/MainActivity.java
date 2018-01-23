package com.example.sandy.recordstore.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.example.sandy.recordstore.activities.albums.AlbumsActivity;
import com.example.sandy.recordstore.App;
import com.example.sandy.recordstore.activities.artists.ArtistsActivity;
import com.example.sandy.recordstore.R;
import com.example.sandy.recordstore.adapters.StockAdapter;
import com.example.sandy.recordstore.models.Album;
import com.example.sandy.recordstore.models.Artist;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView stockListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stockListView = findViewById(R.id.list_stock);

        Log.d(this.getClass().toString(), "ON CREATE CALLED");

        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Album> albums = App.get().getDB().albumDao().getAll();
                for (Album album : albums) {
                    Artist artist = App.get().getDB().artistDao().getById(album.getArtistId());
                    album.setArtist(artist);
                    Log.d(this.getClass().toString(), album.getTitle());
                }
                populateStock(albums);
            }
        }).start();
    }

    private void populateStock(final List<Album> albums) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                StockAdapter stockAdapter = new StockAdapter(getBaseContext(), albums);
                stockListView.setAdapter(stockAdapter);
            }
        });
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

    public void onClickAlbums(View view) {
        Intent intent = new Intent(MainActivity.this, AlbumsActivity.class);
        startActivity(intent);
    }
}
