package com.example.sandy.recordstore.adapters;

import com.example.sandy.recordstore.App;
import com.example.sandy.recordstore.R;
import com.example.sandy.recordstore.activities.MainActivity;
import com.example.sandy.recordstore.activities.albums.AlbumsActivity;
import com.example.sandy.recordstore.models.Album;
import com.example.sandy.recordstore.models.Artist;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by sandy on 23/01/2018.
 */

public class StockAdapter extends ArrayAdapter<Album> {

    public StockAdapter(Context context, List<Album> albums) {
        super(context, 0, albums);
    }

    @Override
    public View getView(int position, View listItemView, final ViewGroup parent) {
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.stock_item, parent, false);
        }

        final Album currentAlbum = getItem(position);
        Log.d(this.getClass().toString(),currentAlbum.getTitle());

        TextView title = listItemView.findViewById(R.id.txt_title);
        title.setText(currentAlbum.getTitle());

        TextView artistText = listItemView.findViewById(R.id.txt_artist);
        artistText.setText(currentAlbum.getArtist().getName());

        TextView stockLevelText = listItemView.findViewById(R.id.txt_level);
        stockLevelText.setText(currentAlbum.stockLevel());

        listItemView.setTag(currentAlbum);
        return listItemView;
    }
}
