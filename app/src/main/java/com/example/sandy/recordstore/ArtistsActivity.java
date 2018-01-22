package com.example.sandy.recordstore;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.sandy.recordstore.models.Artist;

import java.util.List;

public class ArtistsActivity extends AppCompatActivity {

    ListView artistListView;
    Button newArtistButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artists);

        artistListView = findViewById(R.id.artists_list);
        newArtistButton = findViewById(R.id.btn_add_artist);

        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Artist>artists = App.get().getDB().artistDao().getAll();
                for (Artist artist : artists) {
                    Log.d(this.getClass().toString(), artist.getName());
                }
                populateArtists(artists);
            }
        }).start();
    }

    private void populateArtists(final List<Artist> artists) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ArtistAdapter artistAdapter = new ArtistAdapter(getBaseContext(), artists);
                artistListView.setAdapter(artistAdapter);
            }
        });
    }

    public void onClickNewArtistButton(View view) {
        Intent intent = new Intent(ArtistsActivity.this, NewArtistActivity.class);
        startActivity(intent);
    }

    public void onListItemClick(View listItem) {
        Artist artist = (Artist) listItem.getTag();
        Log.d(this.getClass().toString(), artist.getName());

        Intent intent = new Intent(this, ViewArtistActivity.class);
        intent.putExtra("artist", artist);
        startActivity(intent);
    }
}
