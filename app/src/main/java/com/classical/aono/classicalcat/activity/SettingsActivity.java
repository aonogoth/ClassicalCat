package com.classical.aono.classicalcat.activity;

import android.app.Activity;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.RingtonePreference;
import android.support.design.widget.NavigationView;
import android.support.v4.app.NavUtils;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.classical.aono.classicalcat.MaterialApplication;
import com.classical.aono.classicalcat.R;
import com.classical.aono.classicalcat.fragment.AboutFragment;
import com.classical.aono.classicalcat.fragment.BooksFragment;
import com.classical.aono.classicalcat.fragment.CategoryGdmzFragment;
import com.classical.aono.classicalcat.fragment.CategoryJdmzFragment;
import com.classical.aono.classicalcat.fragment.CategoryZtmzFragment;
import com.classical.aono.classicalcat.fragment.SettingFragment;

/**
 * Created by gotha on 2017/10/29.
 */

public class SettingsActivity  extends AppCompatActivity {
    //private SwipeBackLayout mSwipeBackLayout;
    private Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //ButterKnife.bind(this);
        setContentView(R.layout.activity_settings);
        //setTitle("设置");
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mToolbar.setTitle("设置");

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_frame, new SettingFragment())
                .commit();
//        mSwipeBackLayout = getSwipeBackLayout();
//        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
    }



}
