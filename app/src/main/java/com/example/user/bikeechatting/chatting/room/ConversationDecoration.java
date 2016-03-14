package com.example.user.bikeechatting.chatting.room;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by User on 2016-03-14.
 */
public class ConversationDecoration extends RecyclerView.ItemDecoration {
    private int space;

    public ConversationDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.bottom = space;
    }
}
