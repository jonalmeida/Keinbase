package com.jonalmeida.keinbase;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class KeybaseSearchAdapter extends RecyclerView.Adapter<KeybaseSearchAdapter.SearchViewHolder> {

    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class SearchViewHolder extends RecyclerView.ViewHolder {

        public SearchViewHolder(View itemView) {
            super(itemView);
        }
    }
}
