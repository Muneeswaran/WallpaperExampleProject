package com.khushnish.wallpaperexample.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.khushnish.wallpaperexample.PhotoActivity;
import com.khushnish.wallpaperexample.R;
import com.khushnish.wallpaperexample.adapter.PhotoAdapter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class PhotoFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.photo_fragment, container, false);
        initializeComponents(view);

        return view;
    }

    private void initializeComponents(View view) {
        final GridView gridView = (GridView) view.findViewById(R.id.photo_fragment_grid);
        gridView.setEmptyView(view.findViewById(R.id.empty_list_view));

        try {
            final String[] photosArray = getActivity().getAssets().list(getString(R.string.wallpapaer_assets));
            final List<String> photos = Arrays.asList(photosArray);
            gridView.setAdapter(new PhotoAdapter(getActivity(), R.layout.row_photo_fragment,
                    R.id.row_photo_fragment_image, photos));

            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    final Intent photoIntent = new Intent(getActivity(), PhotoActivity.class);
                    photoIntent.putExtra("assestsPhotoName", photos.get(position));
                    startActivity(photoIntent);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}