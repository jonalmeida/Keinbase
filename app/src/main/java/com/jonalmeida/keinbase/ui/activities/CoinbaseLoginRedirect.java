package com.jonalmeida.keinbase.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.coinbase.android.sdk.OAuth;
import com.coinbase.api.entity.OAuthTokensResponse;
import com.coinbase.api.exception.UnauthorizedException;
import com.jonalmeida.keinbase.R;
import com.jonalmeida.keinbase.util.ApiKey;
import com.jonalmeida.keinbase.util.Constants;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class CoinbaseLoginRedirect extends AppCompatActivity {
    private static final String LOGTAG = CoinbaseLoginRedirect.class.getSimpleName();
    private final Context mContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coinbase_login_redirect);
        Intent intent = getIntent();
        String action = intent.getAction();
        Uri data = intent.getData();
        Log.d(LOGTAG, "We got an intent to process: ");
        Log.d(LOGTAG, "intent: " + intent.toString() + "\n\t" +
                        "action: " + action + "\n\t" +
                        "data: " + data.toString());
        new CompleteAuthorizationTask(intent).execute();
    }

    @Override
    protected void onNewIntent(final Intent intent) {
        if (intent != null &&
                intent.getAction() != null &&
                intent.getAction().equals("android.intent.action.VIEW")) {
            new CompleteAuthorizationTask(intent).execute();
        }
    }

    public class CompleteAuthorizationTask extends AsyncTask<Void, Void, OAuthTokensResponse> {

        Intent mIntent;

        CompleteAuthorizationTask(Intent intent) {
            mIntent = intent;
        }

        @Override
        protected OAuthTokensResponse doInBackground(Void... voids) {
            try {
                return OAuth.completeAuthorization(CoinbaseLoginRedirect.this, ApiKey.getClientId(),
                        ApiKey.getClientSecret(), mIntent.getData());
            } catch (UnauthorizedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(OAuthTokensResponse response) {
            if (response.getAccessToken() != null) {
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(mContext);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString(Constants.PREF_COINBASE_CLIENT_KEY, response.getAccessToken());
                editor.apply();
                (findViewById(R.id.redirect_tv_waiting)).setVisibility(View.GONE);
                (findViewById(R.id.redirect_tv_success)).setVisibility(View.VISIBLE);
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        finish();
                    }
                }, 1000);
            }
        }
    }
}
