package com.jonalmeida.keinbase.ui.recycler;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.fasterxml.jackson.databind.JsonNode;
import com.jonalmeida.keinbase.util.ItemClickSupport;
import com.jonalmeida.keinbase.util.JsonSerializer;
import com.jonalmeida.keinbase.KeybaseSearchAdapter;
import com.jonalmeida.keinbase.KeybaseSearchManager;
import com.jonalmeida.keinbase.pojos.Completion;

import java.io.IOException;
import java.util.List;

public class KeybaseSearchResultsView extends RecyclerView
        implements KeybaseSearchManager.Response,
        ItemClickSupport.OnItemClickListener {
    private static final String LOGTAG = KeybaseSearchResultsView.class.getSimpleName();

    private final KeybaseSearchAdapter mAdapter;
    private final Context mContext;
    private final Handler mMainThread;

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
        mMainThread = new Handler(Looper.getMainLooper());
        setLayoutManager(new LinearLayoutManager(context));
        setAdapter(mAdapter);
        ItemClickSupport.addTo(this).setOnItemClickListener(this);
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
            mMainThread.post(new Runnable() {
                @Override
                public void run() {
                    smoothScrollToPosition(0);
                }
            });
            mAdapter.setUserCompletions(completionList);
        } catch (IOException e) {
            Log.e(LOGTAG, "Serializing autocomplete list failed.");
            e.printStackTrace();
        }
    }

    @Override
    public void onItemClicked(RecyclerView recyclerView, int position, View v) {

    }
}
