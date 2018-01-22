package com.example.sandy.recordstore;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.sandy.recordstore.models.Album;
import com.example.sandy.recordstore.models.Artist;

public class ViewAlbumActivity extends AppCompatActivity {

    TextView titleTextView;
    TextView artistTextView;
    TextView quantityTextView;
    Button editButton;
    Button deleteButton;
    Button backButton;
    Album album;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_album);

        titleTextView = findViewById(R.id.txt_title);
        artistTextView = findViewById(R.id.txt_artist);
        quantityTextView = findViewById(R.id.txt_quantity);
        editButton = findViewById(R.id.button_edit);
        deleteButton = findViewById(R.id.button_delete);
        backButton = findViewById(R.id.button_back);

        Intent intent = getIntent();
        this.album = (Album)intent.getSerializableExtra("album");
        Log.d(this.getClass().toString(), this.album.getTitle());
        titleTextView.setText(this.album.getTitle());

        quantityTextView.setText(String.valueOf(this.album.getQuantity()));

        new Thread(new Runnable() {
            @Override
            public void run() {
                Artist artist = App.get().getDB().artistDao().getById(album.getArtistId());
                goBackToList();
                artistTextView.setText(artist.getName());
            }
        }).start();
    }

    public void onEditButtonClick(View view) {
        Intent intent = new Intent(ViewAlbumActivity.this, EditAlbumActivity.class);
        intent.putExtra("album", this.album);
        startActivity(intent);
    }

    public void onDeleteButtonClick(View view) {
        final Album albumToDelete = this.album;
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d(this.getClass().toString(), "Deleting Album: " + albumToDelete.getTitle());
                App.get().getDB().albumDao().delete(albumToDelete);
                goBackToList();
            }
        }).start();
    }

    public void onBackButtonClick(View view) {
        this.goBackToList();
    }

    private void goBackToList() {
        Intent intent = new Intent(ViewAlbumActivity.this, AlbumsActivity.class);
        startActivity(intent);
    }
}
