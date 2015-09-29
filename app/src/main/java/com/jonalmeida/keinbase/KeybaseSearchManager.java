package com.jonalmeida.keinbase;

import android.support.annotation.NonNull;
import android.util.Log;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

import java.io.IOException;

public class KeybaseSearchManager<T extends KeybaseSearchManager.Response> {

    private static final String LOGTAG = KeybaseSearchManager.class.getSimpleName();

    public static final int URL_USER_LOOKUP = 0;
    public static final int URL_USER_DISCOVER = 1;

    public static final int USERNAME_SEARCH = 0;

    private final OkHttpClient mClient;
    private String mUrl;
    private T mCallback;

    private KeybaseSearchManager(@NonNull String url,
                                 @NonNull T callback) {
        mClient = new OkHttpClient();
        mUrl = url;
        mCallback = callback;
    }

    public void execute(int searchType, String queryParam) {
        if (searchType == USERNAME_SEARCH) {
            mUrl += Constants.URL_ATTR_USERNAMES + "=" + queryParam;
        }
        final Request request = new Request.Builder()
                .url(mUrl)
                .build();
        Log.d(LOGTAG, "This is our query URL: " + mUrl);
        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                // Handle it here somehow?
                Log.d(LOGTAG, "Network request failed: " + e.toString());
            }

            @Override
            public void onResponse(com.squareup.okhttp.Response response) throws IOException {
                mCallback.onResponseReceived(response.body().string());
            }
        });
    }

    public static class Builder<P extends Response> {
        private String url;
        private P callback;

        public Builder url(int urlId) {
            this.url = getBaseUrl(urlId);
            return this;
        }

        public Builder callback(P callback) {
            this.callback = callback;
            return this;
        }

        public KeybaseSearchManager build() {
            return new KeybaseSearchManager(this.url, this.callback);
        }
    }

    @NonNull
    private static String getBaseUrl(int lookup) {
        switch (lookup) {
            case URL_USER_LOOKUP:
                return Constants.BASE_URL_USER_LOOKUP;
            case URL_USER_DISCOVER:
                return Constants.BASE_URL_USER_DISCOVER;
            default:
                return Constants.BASE_URL_USER_LOOKUP + "?" + Constants.URL_ATTR_USERNAMES + "=";
        }
    }

    public interface Response {
        void onResponseReceived(String json);
    }
}
