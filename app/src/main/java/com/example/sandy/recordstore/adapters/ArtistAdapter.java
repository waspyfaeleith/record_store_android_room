package com.example.sandy.recordstore.adapters;

import android.content.Context;
import android.graphics.Movie;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.sandy.recordstore.R;
import com.example.sandy.recordstore.models.Artist;

import java.util.List;

/**
 * Created by sandy on 21/01/2018.
 */

public class ArtistAdapter extends ArrayAdapter<Artist> {
    public ArtistAdapter(Context context, List<Artist> artists) {
        super(context, 0, artists);
    }

    @Override
    public View getView(int position, View listItemView, ViewGroup parent) {
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.artist_item, parent, false);
        }

        Artist currentArtist = getItem(position);
        Log.d(this.getClass().toString(),currentArtist.getName());

        TextView name = listItemView.findViewById(R.id.name);
        name.setText(currentArtist.getName());

        listItemView.setTag(currentArtist);
        return listItemView;
    }
}
