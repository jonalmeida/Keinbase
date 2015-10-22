package com.jonalmeida.keinbase;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.jonalmeida.keinbase.ui.recycler.KeybaseSearchResultsView;

import java.util.Timer;
import java.util.TimerTask;


public class KeybaseSearchFragment extends Fragment {
    private static final String LOGTAG = KeybaseSearchFragment.class.getSimpleName();

    KeybaseSearchResultsView mRecyclerView;
    KeybaseSearchManager mSearchManager;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_keybase_search, container, false);
        setSearchListener(rootView);
        mRecyclerView = (KeybaseSearchResultsView) rootView.findViewById(R.id.rv_search_results);
        mSearchManager = new KeybaseSearchManager.Builder()
                .urlType(KeybaseSearchManager.URL_AUTOCOMPLETE)
                .callback(mRecyclerView)
                .build();
        return rootView;
    }

    private void setSearchListener(final View rootView) {
        EditText editText = (EditText) rootView.findViewById(R.id.edit_text_keybase_search);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            private Timer timer = new Timer();

            @Override
            public void afterTextChanged(final Editable editable) {
                timer.cancel();
                if (editable.length() == 0) {
                    return;
                }
                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Log.d(LOGTAG, "Running query now for string: " + editable.toString());
//                        mSearchManager.execute(
//                                KeybaseSearchManager.SEARCH_USERNAMES,
//                                editable.toString());
                        mSearchManager.execute(KeybaseSearchManager.URL_AUTOCOMPLETE,
                                editable.toString());
                    }
                }, 1000);
            }
        });
    }

//    @Override
//    public void onDestroyView() {
//        mRecyclerView.setAdapter(null);
//        mRecyclerView = null;
//
//        mSearchManager = null;
//    }
}
