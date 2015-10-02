package com.jonalmeida.keinbase;

import android.support.annotation.NonNull;
import android.util.Log;

import com.fasterxml.jackson.databind.JsonNode;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

import java.io.IOException;
import java.util.List;

public class KeybaseSearchManager {

    private static final String LOGTAG = KeybaseSearchManager.class.getSimpleName();

    public static final int URL_USER_LOOKUP     = 0;
    public static final int URL_USER_DISCOVER   = 1;

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

    private KeybaseSearchManager(int urlType, @NonNull Response callback) {
        mClient = new OkHttpClient();
        mCallback = callback;
        mBaseUrl = getBaseUrl(urlType);
        mSerializer = JsonSerializer.instance();
    }

    public void execute(int searchType, String queryParams) {
        final String searchUrl = setUrlParamValues(
                getLookupUrl(searchType, mBaseUrl), queryParams);
        final Request request = new Request.Builder()
                .url(searchUrl)
                .build();
        Log.d(LOGTAG, "This is our query URL: " + searchUrl);
        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                // Handle it here somehow?
                // TODO: Change the background to show a network error graphic
                Log.d(LOGTAG, "Network request failed: " + e.toString());
            }

            @Override
            public void onResponse(com.squareup.okhttp.Response response) throws IOException {
                final JsonNode node = mSerializer.readTree(response.body().byteStream());
                List<User> userList = mSerializer.serializeUsersFromResponse(node);

                Log.d(LOGTAG, "Size of users: " + userList.size());
                Log.d(LOGTAG, "Trying to access first user: " + (userList.isEmpty() ? "list is " +
                        "empty" : userList.get(0).getId()));
                Log.d(LOGTAG, "Picture url" + userList.get(0).getPictures().getPrimaryUrl());

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

    private static String getLookupUrl(int lookup, String url) {
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
                break;
        }
        url += "=";
        return url;
    }

    private static String setUrlParamValues(String url, final String params) {
        String[] split = params.split(" |,");
        for(String param : split) {
            url += param + ",";
        }
        // Remove trailing comma
        return url.substring(0, url.length() - 1);
    }

    public interface Response {
        void onResponseReceived(JsonNode json);
    }
}
