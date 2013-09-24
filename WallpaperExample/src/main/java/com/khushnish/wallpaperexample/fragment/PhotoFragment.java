package com.khushnish.wallpaperexample.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.khushnish.wallpaperexample.R;

public class PhotoFragment extends Fragment {

    public PhotoFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.photo_fragment, container, false);

        return view;
    }
}