package com.jonalmeida.keinbase;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.util.List;

public class KeybaseSearchResultsView extends RecyclerView
        implements KeybaseSearchManager.Response {
    private static final String LOGTAG = KeybaseSearchResultsView.class.getSimpleName();

    private final KeybaseSearchAdapter mAdapter;
    private final Context mContext;

    public KeybaseSearchResultsView(Context context) {
        super(context);

        mAdapter = new KeybaseSearchAdapter(context);
        mContext = context;
        setLayoutManager(new LinearLayoutManager(context));
        setAdapter(null);
    }

    @Override
    public void onResponseReceived(JsonNode json) {
        Log.d(LOGTAG, "We got a response from Keybase");
        mAdapter.emptyResults();
        try {
            final List<User> userList = JsonSerializer.instance().serializeUsersFromResponse(json);
//            this.runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
                    mAdapter.setUserResults(userList);
                    mAdapter.notifyDataSetChanged();
//                }
//            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
