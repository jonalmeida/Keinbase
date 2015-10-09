package com.jonalmeida.keinbase;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;

import com.fasterxml.jackson.databind.JsonNode;
import com.jonalmeida.keinbase.pojos.Completion;
import com.jonalmeida.keinbase.pojos.User;

import java.io.IOException;
import java.util.List;

public class KeybaseSearchResultsView extends RecyclerView
        implements KeybaseSearchManager.Response {
    private static final String LOGTAG = KeybaseSearchResultsView.class.getSimpleName();

    private final KeybaseSearchAdapter mAdapter;
    private final Context mContext;

    public KeybaseSearchResultsView(Context context) {
        this(context, null);
    }

    public KeybaseSearchResultsView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public KeybaseSearchResultsView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mAdapter = new KeybaseSearchAdapter(context);
        mContext = context;
        setLayoutManager(new LinearLayoutManager(context));
        setAdapter(mAdapter);
    }

    @Override
    public void onResponseReceived(final JsonNode json) {
        Log.d(LOGTAG, "We got a response from Keybase");
        mAdapter.emptyResults();
//        try {
//            final List<User> userList = JsonSerializer.instance().serializeUsersFromResponse(json);
//            mAdapter.setUserResults(userList);
//        } catch (IOException e) {
//            Log.e(LOGTAG, "Serializing user list failed.");
//            e.printStackTrace();
//        }

        try {
            final List<Completion> completionList = JsonSerializer.instance().serializeCompletionsFromResponse(json);
            mAdapter.setUserCompletions(completionList);
        } catch (IOException e) {
            Log.e(LOGTAG, "Serializing autocomplete list failed.");
            e.printStackTrace();
        }
    }
}
