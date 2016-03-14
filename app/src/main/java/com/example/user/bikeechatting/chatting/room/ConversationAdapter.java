package com.example.user.bikeechatting.chatting.room;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.bikeechatting.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 2016-03-14.
 */
public class ConversationAdapter extends RecyclerView.Adapter<ConversationViewHolder> {
    private List<ConversationItem> list;

    public ConversationAdapter() {
        list = new ArrayList<>();
    }

    @Override
    public ConversationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_conversation_item, parent, false);

        ConversationViewHolder conversationViewHolder = new ConversationViewHolder(view);

        return conversationViewHolder;
    }

    @Override
    public void onBindViewHolder(ConversationViewHolder holder, int position) {
        holder.setView(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void add(ConversationItem item) {
        list.add(item);
        notifyDataSetChanged();
    }
}
