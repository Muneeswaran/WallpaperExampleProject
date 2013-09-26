package com.khushnish.wallpaperexample.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.khushnish.wallpaperexample.R;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class PhotoAdapter extends ArrayAdapter<String> {

    private List<String> photos;
    private LayoutInflater inflater;
    private Context context;

    public PhotoAdapter(Context context, int resource, int textViewResourceId, List<String> photos) {
        super(context, resource, textViewResourceId, photos);
        this.context = context;
        this.photos = photos;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if ( convertView == null ) {
            convertView = inflater.inflate(R.layout.row_photo_fragment, parent, false);
            holder = new ViewHolder();
            holder.image = (ImageView) convertView.findViewById(R.id.row_photo_fragment_image);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        InputStream ims = null;
        try {
            ims = context.getAssets().open(context.getString(R.string.wallpapaer_assets)
                    + File.separator + photos.get(position));
            final Drawable d = Drawable.createFromStream(ims, null);
            holder.image.setImageDrawable(d);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if ( ims != null ) {
                try {
                    ims.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return convertView;
    }

    @Override
    public int getCount() {
        return photos.size();
    }

    private static class ViewHolder {
        private ImageView image;
    }
}