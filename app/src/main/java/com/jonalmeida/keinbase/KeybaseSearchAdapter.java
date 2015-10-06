package com.jonalmeida.keinbase;

import android.content.Context;
import android.support.annotation.UiThread;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class KeybaseSearchAdapter extends RecyclerView.Adapter<KeybaseSearchAdapter.SearchViewHolder> {

    private List<User> mUserResults;
    private Context mContext;

    public KeybaseSearchAdapter(Context context) {
        mUserResults = Collections.emptyList();
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

    public void setUserResults(List<User> userList) {
        mUserResults = userList;
        notifyDataSetChanged();
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

        public void bindItem(User user) {
            mNameTextView.setText(user.getBasics().getUsername());
            mNameTextView.setText((String) user.getProfile().get("full_name"));
            mCoinbaseTextView.setText(getCoinbaseHash(user));
            setProfileImage(user);
        }

        private String getCoinbaseHash(User user) {
            List<Object> bitcoinAddressess = (List<Object>) user.getCryptocurrency_addresses().get("bitcoin");
            if (bitcoinAddressess == null) {
                return "";
            }
            Map<String, String> bitcoinAddress = (Map<String, String>) bitcoinAddressess.get(0);
            return bitcoinAddress.get("address");
//            String address = bitcoinAddress.get("address");
//            return address != null ? address : "";
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
