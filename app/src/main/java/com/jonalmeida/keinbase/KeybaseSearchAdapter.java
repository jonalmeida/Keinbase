package com.jonalmeida.keinbase;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class KeybaseSearchAdapter extends RecyclerView.Adapter<KeybaseSearchAdapter.SearchViewHolder> {

    private List<User> mUserResults;
    private Context mContext;
    private Handler mMainThread;

    public KeybaseSearchAdapter(Context context) {
        mUserResults = Collections.emptyList();
        mMainThread = new Handler(Looper.getMainLooper());
        mContext = context;
    }

    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.card_user, parent, false);
        return new SearchViewHolder(v);
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, int position) {
        holder.bindItem(mUserResults.get(position));
    }

    @Override
    public int getItemCount() {
        return mUserResults.size();
    }

    public void emptyResults() {
        mUserResults.clear();
    }

    public void setUserResults(final List<User> userList) {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mUserResults = userList;
                notifyDataSetChanged();
            }
        });
    }

    public static class SearchViewHolder extends RecyclerView.ViewHolder {
        private final CardView mCardView;
        private final TextView mNameTextView;
        private final TextView mCoinbaseTextView;
        private final ImageView mProfileImageView;
        private final View mItemView;

        public SearchViewHolder(View itemView) {
            super(itemView);
            mItemView = itemView;
            mCardView = (CardView) itemView.findViewById(R.id.card_user_info);
            mNameTextView = (TextView) itemView.findViewById(R.id.tv_user_name);
            mCoinbaseTextView = (TextView) itemView.findViewById(R.id.tv_user_coinbase);
            mProfileImageView = (ImageView) itemView.findViewById(R.id.iv_user_photo);
        }

        public void bindItem(final User user) {
            mNameTextView.setText(user.getBasics().getUsername());
            mNameTextView.setText(user.getProfile().getFull_name());
            setProfileImage(user);
            final String bitcoinHash = getCoinbaseHash(user);
            if (bitcoinHash != null) {
                mCoinbaseTextView.setText(bitcoinHash);
            }
        }

        private @Nullable String getCoinbaseHash(final User user) {
            CryptoCurrency cryptoCurrency = user.getCryptocurrency_addresses();
            if (cryptoCurrency == null) {
                return null;
            }
            List<Bitcoin> addresses = cryptoCurrency.getBitcoin();
            if (addresses.size() == 0) {
                return null;
            }

            // Return the first address we find.
            return addresses.get(0).getAddress();
        }

        private void setProfileImage(User user) {
            String imageUrl = user.getPictures().getPrimaryUrl();
            if (imageUrl == null || imageUrl.length() == 0) {
                return;
            }
            Picasso.with(mItemView.getContext()).load(imageUrl).into(mProfileImageView);
        }
    }
}
