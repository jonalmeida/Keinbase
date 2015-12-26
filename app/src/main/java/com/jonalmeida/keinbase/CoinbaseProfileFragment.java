package com.jonalmeida.keinbase;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.coinbase.android.sdk.OAuth;
import com.coinbase.api.Coinbase;
import com.coinbase.api.CoinbaseBuilder;
import com.coinbase.api.entity.User;
import com.coinbase.api.exception.CoinbaseException;
import com.coinbase.api.exception.UnauthorizedException;
import com.jonalmeida.keinbase.util.ApiKey;
import com.jonalmeida.keinbase.util.Constants;

import java.io.IOException;

public class CoinbaseProfileFragment extends Fragment {

    private static final String LOGTAG = CoinbaseProfileFragment.class.getSimpleName();
    private String OAUTH_TOKEN;

    private SharedPreferences mPrefs;
    //private boolean mInitialSetup = true;
    private MainViewPagerAdapter.RequestReAuthListener mReAuthListener;

    public static CoinbaseProfileFragment newInstance(MainViewPagerAdapter.RequestReAuthListener listener) {
        Bundle args = new Bundle();
        CoinbaseProfileFragment fragment = new CoinbaseProfileFragment();
        fragment.setReAuthListener(listener);
        fragment.setArguments(args);
        return fragment;
    }

    @SuppressLint("ValidFragment")
    public CoinbaseProfileFragment(Context context) {
        if (mPrefs == null) {
            mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        }
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.remove(Constants.PREF_COINBASE_CLIENT_KEY);
        editor.apply();
    }

    public CoinbaseProfileFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View rootView;
        // Check if logged in, then inflate view accordingly
        mPrefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        if (mPrefs.contains(Constants.PREF_COINBASE_CLIENT_KEY)) {
            OAUTH_TOKEN = mPrefs.getString(Constants.PREF_COINBASE_CLIENT_KEY, null);
            rootView = inflater.inflate(R.layout.fragment_coinbase_profile, container, false);
            setupProfileView(rootView);
        } else {
            rootView = inflater.inflate(R.layout.fragment_coinbase_sign_in, container, false);
            //mInitialSetup = false;
            setLoginListeners(rootView);
        }
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
//        if (mInitialSetup) {
//            return;
//        }
//        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//        mPrefs = PreferenceManager.getDefaultSharedPreferences(getContext());
//        if (mPrefs.contains(ApiKey.COINBASE_CLIENT_KEY)) {
//            OAUTH_TOKEN = mPrefs.getString(ApiKey.COINBASE_CLIENT_KEY, null);
//            FragmentTransaction transaction = fragmentManager.beginTransaction();
//            transaction.replace(R.id.fragment_coinbase_signin, new CoinbaseProfileFragment());
//            transaction.addToBackStack(null);
//            transaction.commit();
//        }
    }

    private void setupProfileView(View view) {
        CardView cardView =
                (CardView) view.findViewById(R.id.coinbase_profile_card_view);
        new DisplayUserProfileTask(cardView).execute(OAUTH_TOKEN);
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

    public void setReAuthListener(MainViewPagerAdapter.RequestReAuthListener listener) {
        mReAuthListener = listener;
    }

    class DisplayUserProfileTask extends AsyncTask<String, Void, Void> {

        CardView cardView;
        TextView titleTextView;

        public DisplayUserProfileTask(CardView view) {
            cardView = view;
        }

        @Override
        protected void onPreExecute() {
            titleTextView = (TextView) cardView.findViewById(R.id.coinbase_profile_username);
        }

        @Override
        protected Void doInBackground(String... strings) {
            if (strings.length > 0 && OAUTH_TOKEN != null) {
                Log.d(LOGTAG, "Using token: " + strings[0]);
                String authToken = strings[0];
                final Coinbase coinbase = new CoinbaseBuilder().withAccessToken(authToken).build();
                try {
                    setProfileCard(coinbase.getUser());
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (UnauthorizedException e) {
                    Log.e(LOGTAG, "Coinbase token may have expired, requesting reauth..");
                    mReAuthListener.requestReAuth();
                    //e.printStackTrace();
                } catch (CoinbaseException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        private void setProfileCard(final User user) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    titleTextView.setText(user.getUsername());
                }
            });
        }
    }
}
