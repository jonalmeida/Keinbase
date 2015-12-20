package com.jonalmeida.keinbase;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.coinbase.android.sdk.OAuth;
import com.coinbase.api.Coinbase;
import com.coinbase.api.CoinbaseBuilder;
import com.coinbase.api.exception.CoinbaseException;
import com.jonalmeida.keinbase.util.ApiKey;

import java.io.IOException;

public class CoinbaseProfileFragment extends Fragment {

    private static final String LOGTAG = CoinbaseProfileFragment.class.getSimpleName();
    private String OAUTH_TOKEN;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View rootView;
        // Check if logged in, then inflate view accordingly
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        if (prefs.contains(ApiKey.COINBASE_CLIENT_KEY)) {
            OAUTH_TOKEN = prefs.getString(ApiKey.COINBASE_CLIENT_KEY, null);
            rootView = inflater.inflate(R.layout.fragment_coinbase_profile, container, false);
            setupProfileView(rootView);
        } else {
            rootView = inflater.inflate(R.layout.fragment_coinbase_sign_in, container, false);
            setLoginListeners(rootView);
        }
        return rootView;
    }

    private void setupProfileView(View view) {
        TextView textView = (TextView) view.findViewById(R.id.tv_coinbase_username);
        new DisplayEmailTask(textView).execute(OAUTH_TOKEN);
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

    class DisplayEmailTask extends AsyncTask<String, Void, Void> {

        TextView textView;

        public DisplayEmailTask(TextView view) {
            textView = view;
        }

        @Override
        protected Void doInBackground(String... strings) {
            if (strings.length > 0 && OAUTH_TOKEN != null) {
                Log.d(LOGTAG, "Using token: " + strings[0]);
                String authToken = strings[0];
                final Coinbase coinbase = new CoinbaseBuilder().withAccessToken(authToken).build();
                try {
                    setTextView(coinbase.getUser().getEmail());
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (CoinbaseException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        private void setTextView(final String email) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    textView.setText(email);
                }
            });
        }
    }
}
