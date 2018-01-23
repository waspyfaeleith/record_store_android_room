package com.example.sandy.recordstore.activities.albums;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.sandy.recordstore.App;
import com.example.sandy.recordstore.R;
import com.example.sandy.recordstore.activities.MainActivity;
import com.example.sandy.recordstore.adapters.AlbumAdapter;
import com.example.sandy.recordstore.models.Album;
import com.example.sandy.recordstore.models.Artist;

import java.util.List;

public class AlbumsActivity extends AppCompatActivity {

    ListView albumListView;
    Button newAlbumButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_albums);

        albumListView = findViewById(R.id.albums_list);
        newAlbumButton = findViewById(R.id.btn_add_album);

        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Album> albums = App.get().getDB().albumDao().getAll();
                for (Album album : albums) {
                    Log.d(this.getClass().toString(), album.getTitle());
                    Artist artist = App.get().getDB().artistDao().getById(album.getArtistId());
                    album.setArtist(artist);
                    Log.d(this.getClass().toString(), album.getTitle());
                }
                populateAlbums(albums);
            }
        }).start();
    }

    private void populateAlbums(final List<Album> albums) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AlbumAdapter albumAdapter = new AlbumAdapter(getBaseContext(), albums);
                albumListView.setAdapter(albumAdapter);
            }
        });
    }

    public void onAddAlbumButtonClicked(View view) {
        Intent intent = new Intent(AlbumsActivity.this, NewAlbumActivity.class);
        startActivity(intent);

    }

    public void onHomeButtonClicked(View view) {
        Intent intent = new Intent(AlbumsActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void onListItemClick(View listItem) {
        Album album = (Album) listItem.getTag();
        Log.d(this.getClass().toString(), album.getTitle());

        Intent intent = new Intent(AlbumsActivity.this, ViewAlbumActivity.class);
        intent.putExtra("album", album);
        startActivity(intent);
    }
}
