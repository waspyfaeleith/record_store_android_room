package com.example.sandy.recordstore.activities.artists;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.sandy.recordstore.App;
import com.example.sandy.recordstore.R;
import com.example.sandy.recordstore.models.Artist;

public class NewArtistActivity extends AppCompatActivity {

    EditText nameEditText;
    Button saveButton;
    Button cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_artist);

        this.nameEditText = findViewById(R.id.artist_name);
        this.saveButton = findViewById(R.id.btn_add_artist);
        this.cancelButton = findViewById(R.id.button_cancel);
    }

    public void onSaveButtonClick(View view) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                String name = nameEditText.getText().toString();
                Artist newArtist = new Artist(name);
                Log.d(this.getClass().toString(), "Adding new Artist: " + newArtist.getName());
                App.get().getDB().artistDao().insert(newArtist);
                goBackToList();
            }
        }).start();
    }

    public void onCancelButtonClick(View view) {
        this.goBackToList();
    }

    private void goBackToList() {
        Intent intent = new Intent(NewArtistActivity.this, ArtistsActivity.class);
        startActivity(intent);
    }
}
