package com.example.user.bikeechatting.chatting;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 2016-03-11.
 */
public class ChattingRoomAdapter extends RecyclerView.Adapter<ChattingRoomViewHolder> {
    private List<ChattingRoomItem> list;

    public ChattingRoomAdapter() {
        list = new ArrayList<>();
    }

    @Override
    public ChattingRoomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ChattingRoomViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
