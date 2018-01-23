package com.example.sandy.recordstore.activities.artists;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.sandy.recordstore.App;
import com.example.sandy.recordstore.R;
import com.example.sandy.recordstore.models.Artist;

public class EditArtistActivity extends AppCompatActivity {
    TextView nameTextView;
    Button saveButton;
    Button cancelButton;
    Artist artist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_artist);

        nameTextView = findViewById(R.id.edit_name_text);
        saveButton = findViewById(R.id.btn_save);
        cancelButton = findViewById(R.id.btn_cancel);

        Intent intent = getIntent();
        this.artist = (Artist)intent.getSerializableExtra("artist");
        nameTextView.setText(this.artist.getName());
    }

    public void onSaveButtonClick(View v) {
        final Artist artistToUpdate = this.artist;
        new Thread(new Runnable() {
            @Override
            public void run() {
                String name = nameTextView.getText().toString();
                artistToUpdate.setName(name);
                Log.d(this.getClass().toString(), "Updating Artist: " + artistToUpdate.getName());
                App.get().getDB().artistDao().update(artistToUpdate);
                goBackToList();
            }
        }).start();

    }

    public void onCancelButtonClick(View v) {
        this.goBackToList();
    }

    private void goBackToList() {
        Intent intent = new Intent(EditArtistActivity.this, ArtistsActivity.class);
        startActivity(intent);
    }
}
