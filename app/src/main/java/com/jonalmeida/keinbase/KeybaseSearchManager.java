package com.jonalmeida.keinbase;

import android.support.annotation.NonNull;
import android.util.Log;

import com.fasterxml.jackson.databind.JsonNode;
import com.jonalmeida.keinbase.util.Constants;
import com.jonalmeida.keinbase.util.JsonSerializer;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

import java.io.IOException;

public class KeybaseSearchManager {

    private static final String LOGTAG = KeybaseSearchManager.class.getSimpleName();

    public static final int URL_USER_LOOKUP     = 0;
    public static final int URL_USER_DISCOVER   = 1;
    public static final int URL_AUTOCOMPLETE    = 2;

    public static final int SEARCH_USERNAMES    = 0;
    public static final int SEARCH_DOMAIN       = 1;
    public static final int SEARCH_TWITTER      = 2;
    public static final int SEARCH_GITHUB       = 3;
    public static final int SEARCH_REDDIT       = 4;
    public static final int SEARCH_HACKERNEWS   = 5;
    public static final int SEARCH_COINBASE     = 6;
    public static final int SEARCH_FINGERPRINT  = 7;

    private final OkHttpClient mClient;
    private final String mBaseUrl;
    private final Response mCallback;

    private final JsonSerializer mSerializer;

    private KeybaseSearchManager(final int urlType, @NonNull final Response callback) {
        mClient = new OkHttpClient();
        mCallback = callback;
        mBaseUrl = getBaseUrl(urlType);
        mSerializer = JsonSerializer.instance();
    }

    public void execute(final int searchType, final String queryParams) {
//        final String searchUrl = setUrlParamValues(
//                getLookupUrl(searchType, mBaseUrl), queryParams);
        final String searchUrl = setUrlParamValues(mBaseUrl, queryParams);
        final Request request = new Request.Builder()
                .url(searchUrl)
                .build();
        Log.d(LOGTAG, "This is our query URL: " + searchUrl);
        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                // TODO: Change the background to show a network error graphic
                Log.d(LOGTAG, "Network request failed: " + e.toString());
            }

            @Override
            public void onResponse(com.squareup.okhttp.Response response) throws IOException {
                final JsonNode node = mSerializer.readTree(response.body().byteStream());
                mCallback.onResponseReceived(node);
            }
        });
    }

    public static class Builder {
        private int urlType;
        private Response callback;

        public Builder urlType(int urlId) {
            this.urlType = urlId;
            return this;
        }

        public Builder callback(Response callback) {
            this.callback = callback;
            return this;
        }

        public KeybaseSearchManager build() {
            return new KeybaseSearchManager(this.urlType, this.callback);
        }
    }

    @NonNull
    private static String getBaseUrl(final int lookup) {
        switch (lookup) {
            case URL_USER_LOOKUP:
                return Constants.BASE_URL_USER_LOOKUP;
            case URL_USER_DISCOVER:
                return Constants.BASE_URL_USER_DISCOVER;
            case URL_AUTOCOMPLETE:
                return Constants.BASE_URL_AUTOCOMPLETE;
            default:
                return Constants.BASE_URL_USER_LOOKUP + "?" + Constants.URL_ATTR_USERNAMES + "=";
        }
    }

    private static String getLookupUrl(final int lookup, String url) {
        switch (lookup) {
            case SEARCH_USERNAMES:
                url += Constants.URL_ATTR_USERNAMES;
                break;
            case SEARCH_DOMAIN:
                url += Constants.URL_ATTR_DOMAIN;
                break;
            case SEARCH_TWITTER:
                url += Constants.URL_ATTR_TWITTER;
                break;
            case SEARCH_GITHUB:
                url += Constants.URL_ATTR_GITHUB;
                break;
            case SEARCH_REDDIT:
                url += Constants.URL_ATTR_REDDIT;
                break;
            case SEARCH_HACKERNEWS:
                url += Constants.URL_ATTR_HACKERNEWS;
                break;
            case SEARCH_COINBASE:
                url += Constants.URL_ATTR_COINBASE;
                break;
            case SEARCH_FINGERPRINT:
                url += Constants.URL_ATTR_FINGERPRINT;
                break;
            default:
                url += Constants.URL_ATTR_Q;
                break;
        }
        url += "=";
        return url;
    }

    private static String setUrlParamValues(final String url, final String params) {
        final String[] tokens = params.split("[\\W]+");
        String splitString = url;
        for(String param : tokens) {
            splitString += param + ",";
        }
        // Remove trailing comma added
        return splitString.substring(0, splitString.length() - 1);
    }

    public interface Response {
        void onResponseReceived(JsonNode json);
    }
}
