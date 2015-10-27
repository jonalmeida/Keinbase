package com.jonalmeida.keinbase;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainViewPagerAdapter extends FragmentPagerAdapter {

    private static final String LOGTAG = MainViewPagerAdapter.class.getSimpleName();

    private final String[] TITLES = { "Search", "Tracking", "Profile", "Coinbase", "Fifth" };

    public MainViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return TITLES.length;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new KeybaseSearchFragment();
        } else if (position == 3) {
            return new CoinbaseProfileFragment();
        }
        MainPagerFragment fragment = new MainPagerFragment();
        Bundle args = new Bundle();
        args.putInt(MainPagerFragment.ARG_OBJECT, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TITLES[position];
    }


    public static class MainPagerFragment extends Fragment {
        public static final String ARG_OBJECT = "object";
        @Override
        public View onCreateView(LayoutInflater inflater,
                                 ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(
                    R.layout.fragment_pager_main, container, false);
            Bundle args = getArguments();
            ((TextView) rootView.findViewById(R.id.pager_text))
                    .setText(Integer.toString(args.getInt(ARG_OBJECT)));
            return rootView;
        }
    }
}
