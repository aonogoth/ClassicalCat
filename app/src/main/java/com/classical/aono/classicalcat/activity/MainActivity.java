package com.classical.aono.classicalcat.activity;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.classical.aono.classicalcat.MaterialApplication;
import com.classical.aono.classicalcat.R;
import com.classical.aono.classicalcat.fragment.AboutFragment;
import com.classical.aono.classicalcat.fragment.BooksFragment;
import com.classical.aono.classicalcat.fragment.CategoryGdmzFragment;
import com.classical.aono.classicalcat.fragment.CategoryJdmzFragment;
import com.classical.aono.classicalcat.fragment.CategoryZtmzFragment;

public class MainActivity extends AppCompatActivity
{

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private Toolbar mToolbar;
    private NavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);



//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.setDrawerListener(toggle);
//        toggle.syncState();
//
//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open,
                R.string.drawer_close);
        mDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        setupDrawerContent(mNavigationView);

        switchToBook();
    }

    private void switchToBook() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, new BooksFragment()).commit();
        mToolbar.setTitle("所有内容");
    }
    private void switchToBookGdmz() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, new CategoryGdmzFragment()).commit();
        mToolbar.setTitle("古典名著");
    }
    private void switchToBookJdmz() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, new CategoryJdmzFragment()).commit();
        mToolbar.setTitle("近代名著");
    }
    private void switchToBookZtmz() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, new CategoryZtmzFragment()).commit();
        mToolbar.setTitle("推理名著");
    }

    private void switchToAbout() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, new AboutFragment()).commit();
        mToolbar.setTitle("关于");
    }
    private void switchToSetting() {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }


    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {

                            case R.id.nav_camera:
                                switchToBook();
                                break;
                            case R.id.nav_gallery:
                                switchToBookGdmz();
                                break;
                            case R.id.nav_slideshow:
                                switchToBookJdmz();
                                break;
                            case R.id.nav_manage:
                                switchToBookZtmz();
                                break;
                            case R.id.nav_share:
                                switchToAbout();
                                break;
                            case R.id.nav_send:
                                switchToSetting();
                                break;
                        }
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

//    @SuppressWarnings("StatementWithEmptyBody")
//    @Override
//    public boolean onNavigationItemSelected(MenuItem item) {
//        // Handle navigation view item clicks here.
//        int id = item.getItemId();
//
//        if (id == R.id.nav_camera) {
//            // Handle the camera action
//            switchToBook();
//        } else if (id == R.id.nav_gallery) {
//            switchToBook();
//        } else if (id == R.id.nav_slideshow) {
//            switchToBook();
//        } else if (id == R.id.nav_manage) {
//            switchToBook();
//        } else if (id == R.id.nav_share) {
//            switchToBook();
//        } else if (id == R.id.nav_send) {
//            switchToBook();
//        }
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
//        return true;
//    }


}
