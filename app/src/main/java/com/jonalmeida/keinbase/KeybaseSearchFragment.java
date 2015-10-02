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

import com.fasterxml.jackson.databind.JsonNode;

import java.util.Timer;
import java.util.TimerTask;


public class KeybaseSearchFragment extends Fragment implements KeybaseSearchManager.Response {
    private static final String LOGTAG = KeybaseSearchFragment.class.getSimpleName();

    KeybaseSearchManager searchManager = new KeybaseSearchManager.Builder()
            .urlType(KeybaseSearchManager.URL_USER_LOOKUP)
            .callback(this)
            .build();

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_keybase_search, container, false);
        setSearchListener(rootView);
        return rootView;
    }

    private void setSearchListener(View rootView) {
        EditText editText = (EditText) rootView.findViewById(R.id.edit_text_keybase_search);
        editText.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            private Timer timer = new Timer();

            @Override
            public void afterTextChanged(final Editable editable) {
                timer.cancel();
                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Log.d(LOGTAG, "Running query now for string: " + editable.toString());
                        searchManager.execute(
                                KeybaseSearchManager.SEARCH_USERNAMES,
                                editable.toString());
                    }
                }, 1000);
            }
        });
    }

    @Override
    public void onResponseReceived(JsonNode json) {
        Log.d(LOGTAG, "We got a response from Keybase");
        //Log.d(LOGTAG, "Response: " + json.textValue());
    }
}
