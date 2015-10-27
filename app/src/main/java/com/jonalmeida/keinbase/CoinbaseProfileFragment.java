package com.jonalmeida.keinbase;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CoinbaseProfileFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // Check if logged in, then inflate view accordingly
        View rootView = inflater.inflate(R.layout.fragment_coinbase_sign_in, container, false);
        return rootView;
    }
}
