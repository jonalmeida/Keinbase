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

import com.jonalmeida.keinbase.pojos.Bitcoin;
import com.jonalmeida.keinbase.pojos.Completion;
import com.jonalmeida.keinbase.pojos.Component;
import com.jonalmeida.keinbase.pojos.Components;
import com.jonalmeida.keinbase.pojos.CryptoCurrency;
import com.jonalmeida.keinbase.pojos.User;
import com.jonalmeida.keinbase.pojos.WebsiteComponent;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

public class KeybaseSearchAdapter extends RecyclerView.Adapter<KeybaseSearchAdapter.SearchViewHolder> {

    private List<User> mUserResults;
    private List<Completion> mUserCompletions;
    private Context mContext;
    private Handler mMainThread;

    private static final int VIEW_TYPE_USER = 0;
    private static final int VIEW_TYPE_AUTOCOMPLETE = 1;

    public KeybaseSearchAdapter(Context context) {
        mUserResults = Collections.emptyList();
        mUserCompletions = Collections.emptyList();
        mMainThread = new Handler(Looper.getMainLooper());
        mContext = context;
    }

    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_USER:
//                View v1 = LayoutInflater.from(mContext).inflate(R.layout.card_user, parent, false);
//                return new SearchUserViewHolder(v1);
            case VIEW_TYPE_AUTOCOMPLETE:
                View v2 = LayoutInflater.from(mContext).inflate(R.layout.card_user_autocomplete, parent, false);
                return new SearchAutocompleteViewHolder(v2);
            default:
                throw new IllegalArgumentException("Unknown viewType: " + viewType);
        }
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, int position) {
//        if (!mUserResults.isEmpty()) {
//            ((SearchUserViewHolder) holder).bindItem(mUserResults.get(position));
//        } else {
            ((SearchAutocompleteViewHolder) holder).bindItem(mUserCompletions.get(position));
//        }
    }

    @Override
    public int getItemViewType(int position) {
        return !mUserResults.isEmpty() ? VIEW_TYPE_USER : VIEW_TYPE_AUTOCOMPLETE;
    }

    @Override
    public int getItemCount() {
        //return mUserResults != null ? mUserResults.size() : mUserCompletions.size();
        return mUserCompletions.size();
    }

    public void emptyResults() {
        if (!mUserResults.isEmpty()) {
            mUserResults.clear();
        } else {
            mUserCompletions.clear();
        }
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

    public void setUserCompletions(final List<Completion> completions) {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mUserCompletions = completions;
                notifyDataSetChanged();
            }
        });
    }

//    public static class SearchUserViewHolder extends SearchViewHolder {
//        private final TextView mCoinbaseTextView;
//        private final ImageView mProfileImageView;
//
//        public SearchUserViewHolder(View itemView) {
//            super(itemView);
//            mCoinbaseTextView = (TextView) itemView.findViewById(R.id.tv_user_coinbase);
//            mProfileImageView = (ImageView) itemView.findViewById(R.id.iv_user_photo);
//        }
//
//        public void bindItem(final User user) {
//            String username = user.getBasics().getUsername();
//            if (user.getProfile().getFull_name() != null) {
//                username = user.getProfile().getFull_name();
//            }
//            nameTextView.setText(username);
//
//            final String bitcoinHash = getCoinbaseHash(user);
//            if (bitcoinHash != null) {
//                mCoinbaseTextView.setText(bitcoinHash);
//            }
//            setProfileImage(user);
//        }
//
//        private @Nullable String getCoinbaseHash(final User user) {
//            CryptoCurrency cryptoCurrency = user.getCryptocurrency_addresses();
//            if (cryptoCurrency == null) {
//                return null;
//            }
//            List<Bitcoin> addresses = cryptoCurrency.getBitcoin();
//            if (addresses.size() == 0) {
//                return null;
//            }
//
//            // Return the first address we find.
//            return addresses.get(0).getAddress();
//        }
//
//        private void setProfileImage(User user) {
//            String imageUrl = user.getPictures().getPrimaryUrl();
//            if (imageUrl == null || imageUrl.length() == 0) {
//                return;
//            }
//            Picasso.with(itemView.getContext()).load(imageUrl).into(mProfileImageView);
//        }
//    }

    public static class SearchAutocompleteViewHolder extends SearchViewHolder {
        private final TextView mUsernameTextView;
        private final ImageView mThumbnail;
        private final ImageView mBitcoinIcon;
        private final ImageView mGithubIcon;
        private final ImageView mHackerNewsIcon;
        private final ImageView mRedditIcon;
        private final ImageView mWebsiteIcon;
        private final ImageView mTwitterIcon;

        public SearchAutocompleteViewHolder(View itemView) {
            super(itemView);
            mUsernameTextView = (TextView) itemView.findViewById(R.id.tv_user_name);
            mThumbnail = (ImageView) itemView.findViewById(R.id.iv_user_photo);
            mBitcoinIcon = (ImageView) itemView.findViewById(R.id.iv_bitcoin_icon);
            mGithubIcon = (ImageView) itemView.findViewById(R.id.iv_github_icon);
            mHackerNewsIcon = (ImageView) itemView.findViewById(R.id.iv_hn_icon);
            mRedditIcon = (ImageView) itemView.findViewById(R.id.iv_reddit_icon);
            mWebsiteIcon = (ImageView) itemView.findViewById(R.id.iv_website_icon);
            mTwitterIcon = (ImageView) itemView.findViewById(R.id.iv_twitter_icon);
        }

        public void bindItem(final Completion completion) {
            final Components components = completion.getComponents();
            setName(components);
            setThumbnailImage(completion);
            setSocialIcons(components);
        }

        private void setThumbnailImage(final Completion completion) {
            String imageUrl = completion.getThumbnail();
            if (imageUrl == null || imageUrl.length() == 0) {
                mThumbnail.setImageResource(R.drawable.placeholder_avatar);
                return;
            }
            Picasso.with(itemView.getContext())
                    .load(imageUrl)
                    .placeholder(R.drawable.placeholder_avatar)
                    .into(mThumbnail);
        }

        private void setSocialIcons(final Components components) {
            final Component coinbase = components.getCoinbase();
            if (coinbase != null) {
                mBitcoinIcon.setVisibility(View.VISIBLE);
            } else  {
                mBitcoinIcon.setVisibility(View.GONE);
            }
            final Component github = components.getGithub();
            if (github != null) {
                mGithubIcon.setVisibility(View.VISIBLE);
            } else {
                mGithubIcon.setVisibility(View.GONE);
            }
            final Component hn = components.getHackernews();
            if (hn != null) {
                mHackerNewsIcon.setVisibility(View.VISIBLE);
            } else {
                mHackerNewsIcon.setVisibility(View.GONE);
            }
            final Component reddit = components.getReddit();
            if (reddit != null) {
                mRedditIcon.setVisibility(View.VISIBLE);
            } else {
                mRedditIcon.setVisibility(View.GONE);
            }
            final Component twitter = components.getTwitter();
            if (twitter != null) {
                mTwitterIcon.setVisibility(View.VISIBLE);
            } else {
                mTwitterIcon.setVisibility(View.GONE);
            }
            final List<WebsiteComponent> websites = components.getWebsites();
            if (websites != null) {
                mWebsiteIcon.setVisibility(View.VISIBLE);
            } else {
                mWebsiteIcon.setVisibility(View.GONE);
            }
        }

        private void setName(final Components components) {
            final Component nameComponent = components.getFull_name();
            if (nameComponent != null) {
                nameTextView.setText(nameComponent.getVal());
                final String userNameString = "(" + components.getUsername().getVal() + ")";
                mUsernameTextView.setText(userNameString);
                mUsernameTextView.setVisibility(View.VISIBLE);
                return;
            }
            final String name = components.getUsername().getVal();
            nameTextView.setText(name);
        }
    }

    public static class SearchViewHolder extends RecyclerView.ViewHolder {
        protected final CardView cardView;
        protected final TextView nameTextView;

        public SearchViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.card_user_info);
            nameTextView = (TextView) itemView.findViewById(R.id.tv_name);
        }
    }
}
