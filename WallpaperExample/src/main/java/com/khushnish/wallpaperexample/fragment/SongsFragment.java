package com.khushnish.wallpaperexample.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.khushnish.wallpaperexample.R;
import com.khushnish.wallpaperexample.adapter.PhotoAdapter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class SongsFragment extends Fragment {

    public SongsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.songs_fragment, container, false);
        initializeComponents(view);
        
        return view;
    }

    private void initializeComponents(View view) {
        final ListView listView = (ListView) view.findViewById( R.id.songs_fragment_list);
        listView.setEmptyView(view.findViewById(R.id.empty_list_view));

        try {
            final String[] photosArray = getActivity().getAssets().list(
                    getString(R.string.songs_assets));
            final List<String> photos = Arrays.asList(photosArray);
            listView.setAdapter(new PhotoAdapter(getActivity(), R.layout.row_songs_fragment,
                    R.id.row_photo_fragment_image, photos));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}