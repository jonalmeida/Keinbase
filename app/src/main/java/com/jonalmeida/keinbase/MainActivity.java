package com.jonalmeida.keinbase;

import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.coinbase.android.sdk.OAuth;
import com.coinbase.api.exception.CoinbaseException;

public class MainActivity extends AppCompatActivity {

    static final String REDIRECT_URI = "keinbase://coinbase-oauth";
    private static final String CLIENT_ID = "2e9bcf464cd4054c80a2a27cd49ef70934ff68aceef93d176f57ac4922463fb1";

    ViewPager mViewPager;
    PagerAdapter mPageAdapter;
    TabLayout mTabLayout;
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        mToolbar   = (Toolbar)   findViewById(R.id.toolbar);
        mPageAdapter = new MainViewPagerAdapter(getSupportFragmentManager());


        mViewPager.setAdapter(mPageAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        setSupportActionBar(mToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
//        try {
//            OAuth.beginAuthorization(this, CLIENT_ID, "user", REDIRECT_URI, null);
//        } catch (CoinbaseException e) {
//            e.printStackTrace();
//        }
}
