package com.jonalmeida.keinbase;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class KeybaseSearchResultsView extends RecyclerView {
    public KeybaseSearchResultsView(Context context) {
        super(context);
        setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
