package com.example.sandy.recordstore.activities.albums;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.sandy.recordstore.App;
import com.example.sandy.recordstore.R;
import com.example.sandy.recordstore.models.Album;
import com.example.sandy.recordstore.models.Artist;

import java.util.ArrayList;
import java.util.List;

public class NewAlbumActivity extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener {

    EditText titleEditText;
    Spinner artistSpinner;
    EditText quantityEditText;
    Button saveButton;
    Button cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_album);

        titleEditText = findViewById(R.id.txt_title);
        quantityEditText = findViewById(R.id.txt_quantity);
        artistSpinner = findViewById(R.id.spinner_artist);
        saveButton = findViewById(R.id.btn_save);
        cancelButton = findViewById(R.id.btn_cancel);
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Artist> artists = App.get().getDB().artistDao().getAll();
                for (Artist artist : artists) {
                    Log.d(this.getClass().toString(), artist.getName());
                }
                loadSpinnerData(artists);
            }
        }).start();
    }

    private void loadSpinnerData(List<Artist> artists) {
        ArrayList<String> artistNames = new ArrayList();
        for (Artist artist : artists) {
            artistNames.add(artist.getName());
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, artistNames);

        dataAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        this.artistSpinner.setAdapter(dataAdapter);
        this.artistSpinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position,
                               long id) {
        String label = parent.getItemAtPosition(position).toString();

        Toast.makeText(parent.getContext(), "You selected: " + label,
                Toast.LENGTH_LONG).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }

    public void onSaveButtonClicked(View view) {
        final String title = this.titleEditText.getText().toString();
        final String artistName = this.artistSpinner.getSelectedItem().toString();
        final int quantity = Integer.parseInt(quantityEditText.getText().toString());
        //Toast.makeText(NewAlbumActivity.this, "saving: " + title + "-" + artistName + "-" + quantity,
        //       Toast.LENGTH_LONG).show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Artist artist= App.get().getDB().artistDao().getByName(artistName);
                Album album = new Album(title, artist.getId(), quantity);
                App.get().getDB().albumDao().insert(album);
            }
        }).start();

        this.goBackToList();
    }

    public void onCancelButtonClicked(View view) {
        this.goBackToList();
    }

    private void goBackToList() {
        Intent intent = new Intent(NewAlbumActivity.this, AlbumsActivity.class);
        startActivity(intent);
    }
}
