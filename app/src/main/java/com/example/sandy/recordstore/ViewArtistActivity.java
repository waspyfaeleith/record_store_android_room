package com.example.sandy.recordstore;

import android.content.Intent;
import android.graphics.Movie;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.sandy.recordstore.models.Artist;

public class ViewArtistActivity extends AppCompatActivity {

    TextView nameTextView;
    Button editButton;
    Button deleteButton;
    Button backButton;
    Artist artist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_artist);

        nameTextView = findViewById(R.id.artist_name);
        editButton = findViewById(R.id.button_edit);
        deleteButton = findViewById(R.id.button_delete);
        backButton = findViewById(R.id.button_back);

        Intent intent = getIntent();
        this.artist = (Artist)intent.getSerializableExtra("artist");
        Log.d(this.getClass().toString(), this.artist.getName());
        nameTextView.setText(this.artist.getName());
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
