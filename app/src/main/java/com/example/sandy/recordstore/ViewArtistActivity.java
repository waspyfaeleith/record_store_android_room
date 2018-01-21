package com.example.sandy.recordstore;

import android.content.Intent;
import android.graphics.Movie;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.sandy.recordstore.models.Artist;

public class ViewArtistActivity extends AppCompatActivity {

    TextView nameTextView;
    Button editButton;
    Button deleteButton;
    Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_artist);

        nameTextView = findViewById(R.id.artist_name);
        editButton = findViewById(R.id.button_edit);
        deleteButton = findViewById(R.id.button_delete);
        backButton = findViewById(R.id.button_back);

        Intent intent = getIntent();
        Artist artist = (Artist)intent.getSerializableExtra("artist");
        Log.d(this.getClass().toString(), artist.getName());
        nameTextView.setText(artist.getName());
    }
}
