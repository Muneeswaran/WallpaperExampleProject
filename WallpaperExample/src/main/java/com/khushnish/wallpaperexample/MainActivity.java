package com.khushnish.wallpaperexample;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.khushnish.wallpaperexample.fragment.PhotoFragment;
import com.khushnish.wallpaperexample.fragment.SongsFragment;

public class MainActivity extends ActionBarActivity {

    private String[] drawdeItems;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBar actionBar;
    private ActionBarDrawerToggle mDrawerToggle;
    private Fragment photoFragment;
    private Fragment songsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeComponents();

        if (savedInstanceState == null) {
            selectItem(0);
        }
    }

    private void initializeComponents() {
        drawdeItems = getResources().getStringArray(R.array.items);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.activity_main_drawer);

        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, drawdeItems));

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.color.green));
        actionBar.setDisplayShowTitleEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* nav drawer image to replace 'Up' caret */
                R.string.app_name,  /* "open drawer" description for accessibility */
                R.string.app_name  /* "close drawer" description for accessibility */
        ) {
            public void onDrawerClosed(View view) {
//                actionBar.setTitle(mTitle);
            }

            public void onDrawerOpened(View drawerView) {
//                actionBar.setTitle(mDrawerTitle);
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        photoFragment = new PhotoFragment();
        songsFragment = new SongsFragment();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                if ( mDrawerLayout.isDrawerOpen(mDrawerList) ) {
                    mDrawerLayout.closeDrawer(mDrawerList);
                } else {
                    mDrawerLayout.openDrawer(mDrawerList);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /* The click listner for ListView in the navigation drawer */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if ( mDrawerLayout.isDrawerOpen(mDrawerList) ) {
                mDrawerLayout.closeDrawer(mDrawerList);
            }
            selectItem(position);
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    private void selectItem( int pos ) {
        final FragmentManager fragmentManager = getSupportFragmentManager();

        if ( pos == 0 ) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, photoFragment).commit();
        } else if ( pos == 1 ) {

        } else if ( pos == 2 ) {

        } else if ( pos == 3 ) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, songsFragment).commit();
        }
    }
}