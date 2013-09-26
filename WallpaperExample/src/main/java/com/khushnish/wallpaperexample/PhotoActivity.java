package com.khushnish.wallpaperexample;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class PhotoActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        initializeComponents();
    }

    private void initializeComponents() {

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Photos");

        final ImageView photoImage = (ImageView) findViewById(R.id.activity_photo_image);
        InputStream ims = null;
        try {
            ims = getAssets().open(getString(R.string.wallpapaer_assets)
                    + File.separator + getIntent().getStringExtra("assestsPhotoName"));
            final Drawable d = Drawable.createFromStream(ims, null);
            photoImage.setImageDrawable(d);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ims != null) {
                try {
                    ims.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
}