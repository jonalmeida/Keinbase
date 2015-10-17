package com.jonalmeida.keinbase;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;

public class UserCard extends CardView {
    private static final String LOGTAG = UserCard.class.getSimpleName();

    private final Context mContext;
    private final ImageView mUserPhoto;

    public UserCard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        LayoutInflater.from(context).inflate(R.layout.card_user,  this);
        setPreventCornerOverlap(false);
        mUserPhoto = (ImageView) findViewById(R.id.iv_user_photo);
    }

    public UserCard(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public UserCard(Context context) {
        this(context, null, 0);
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    private void correctImageCorners() {
        Rect border = new Rect();
        mUserPhoto.getDrawingRect(border);
    }
}
