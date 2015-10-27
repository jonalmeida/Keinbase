package com.jonalmeida.keinbase;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.coinbase.android.sdk.OAuth;
import com.coinbase.api.exception.CoinbaseException;
import com.jonalmeida.keinbase.util.ApiKey;

public class CoinbaseProfileFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // Check if logged in, then inflate view accordingly
        View rootView = inflater.inflate(R.layout.fragment_coinbase_sign_in, container, false);
        setLoginListeners(rootView);
        return rootView;
    }

    private void setLoginListeners(View view) {
        Button loginButton = (Button) view.findViewById(R.id.coinbase_login_btn_login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    OAuth.beginAuthorization(getActivity(), ApiKey.getClientId(),
                            "user", ApiKey.getRedirectUri(), null);
                } catch (CoinbaseException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
