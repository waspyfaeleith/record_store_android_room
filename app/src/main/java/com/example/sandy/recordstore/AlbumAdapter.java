package com.example.sandy.recordstore;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.sandy.recordstore.models.Album;

import java.util.List;

/**
 * Created by sandy on 22/01/2018.
 */

public class AlbumAdapter extends ArrayAdapter<Album> {
    public AlbumAdapter(Context context, List<Album> albums) {
        super(context, 0, albums);
    }

    @Override
    public View getView(int position, View listItemView, ViewGroup parent) {
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.album_item, parent, false);
        }

        Album currentAlbum = getItem(position);
        Log.d(this.getClass().toString(),currentAlbum.getTitle());

        TextView name = listItemView.findViewById(R.id.title);
        name.setText(currentAlbum.getTitle());

        listItemView.setTag(currentAlbum);
        return listItemView;
    }
}
