package com.jonalmeida.keinbase;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.coinbase.android.sdk.OAuth;
import com.coinbase.api.exception.CoinbaseException;

public class CoinbaseProfileFragment extends Fragment {

    private static final String REDIRECT_URI = "keinbase://coinbase-oauth";
    private static final String CLIENT_ID = "2e9bcf464cd4054c80a2a27cd49ef70934ff68aceef93d176f57ac4922463fb1";

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // Check if logged in, then inflate view accordingly
        View rootView = inflater.inflate(R.layout.fragment_coinbase_sign_in, container, false);
        return rootView;
    }

    private void setLoginListeners(View view) {
        Button loginButton = (Button) view.findViewById(R.id.coinbase_login_btn_login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    OAuth.beginAuthorization(getActivity(), CLIENT_ID, "user", REDIRECT_URI, null);
                } catch (CoinbaseException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
