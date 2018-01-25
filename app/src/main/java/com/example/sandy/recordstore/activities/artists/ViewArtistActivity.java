package com.example.sandy.recordstore.activities.artists;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sandy.recordstore.App;
import com.example.sandy.recordstore.R;
import com.example.sandy.recordstore.adapters.ArtistAlbumAdapter;
import com.example.sandy.recordstore.models.Album;
import com.example.sandy.recordstore.models.Artist;

import java.util.List;

public class ViewArtistActivity extends AppCompatActivity {

    TextView nameTextView;
    Button editButton;
    Button deleteButton;
    Button backButton;
    ListView albumListView;
    Artist artist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_artist);

        nameTextView = findViewById(R.id.artist_name);
        editButton = findViewById(R.id.button_edit);
        deleteButton = findViewById(R.id.button_delete);
        backButton = findViewById(R.id.button_back);
        albumListView = findViewById(R.id.albums_list);

        Intent intent = getIntent();
        this.artist = (Artist)intent.getSerializableExtra("artist");
        Log.d(this.getClass().toString(), this.artist.getName());
        nameTextView.setText(this.artist.getName());

        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Album> albums = App.get().getDB().artistDao().getAlbums(artist.getId());
                for (Album album : albums) {
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
                ArtistAlbumAdapter albumAdapter = new ArtistAlbumAdapter(getBaseContext(), albums);
                albumListView.setAdapter(albumAdapter);
            }
        });
    }

    public void onEditButtonClick(View view) {
        Intent intent = new Intent(this, EditArtistActivity.class);
        intent.putExtra("artist", this.artist);
        startActivity(intent);
    }

    public void onDeleteButtonClick(View view) {
        final Artist artistToDelete = this.artist;
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d(this.getClass().toString(), "Deleting Artist: " + artistToDelete.getName());
                App.get().getDB().artistDao().delete(artistToDelete);
                goBackToList();
            }
        }).start();
    }

    public void onBackButtonClick(View view) {
        this.goBackToList();
    }

    private void goBackToList() {
        Intent intent = new Intent(ViewArtistActivity.this, ArtistsActivity.class);
        startActivity(intent);
    }
}
