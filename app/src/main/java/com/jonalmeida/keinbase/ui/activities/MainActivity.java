package com.jonalmeida.keinbase.ui.activities;

import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.coinbase.android.sdk.OAuth;
import com.coinbase.api.exception.CoinbaseException;
import com.jonalmeida.keinbase.MainViewPagerAdapter;
import com.jonalmeida.keinbase.R;

public class MainActivity extends AppCompatActivity {

    static final String REDIRECT_URI = "keinbase://coinbase-oauth";
    private static final String CLIENT_ID = "2e9bcf464cd4054c80a2a27cd49ef70934ff68aceef93d176f57ac4922463fb1";

    private DrawerLayout mDrawerLayout;
    private ViewPager mViewPager;
    private PagerAdapter mPageAdapter;
    private TabLayout mTabLayout;
    private Toolbar mToolbar;
    private NavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout   = (DrawerLayout)    findViewById(R.id.drawer_layout);
        mViewPager      = (ViewPager)       findViewById(R.id.pager);
        mTabLayout      = (TabLayout)       findViewById(R.id.tabs);
        mToolbar        = (Toolbar)         findViewById(R.id.toolbar);
        mNavigationView = (NavigationView)  findViewById(R.id.nav_view);
        mPageAdapter = new MainViewPagerAdapter(getSupportFragmentManager());

        if (mNavigationView != null) {
            setupDrawerContent(mNavigationView);
        }
        mViewPager.setAdapter(mPageAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
            new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(MenuItem menuItem) {
                    menuItem.setChecked(true);
                    mDrawerLayout.closeDrawers();
                    return true;
                }
            });
    }
//        try {
//            OAuth.beginAuthorization(this, CLIENT_ID, "user", REDIRECT_URI, null);
//        } catch (CoinbaseException e) {
//            e.printStackTrace();
//        }
}
