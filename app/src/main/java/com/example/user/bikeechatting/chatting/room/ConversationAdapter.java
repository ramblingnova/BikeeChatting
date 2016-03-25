package com.example.user.bikeechatting.chatting.room;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.user.bikeechatting.R;
import com.sendbird.android.model.MessageModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 2016-03-14.
 */
public class ConversationAdapter extends RecyclerView.Adapter<ConversationViewHolder> {
    private List<ConversationItem> list;
    public static final int RECEIVE = 1;
    public static final int SEND = 2;
    public static final int DATE = 3;
    public static final int INIT = 4;
    public static final int MID = 5;
    public static final int FINAL = 6;
    private long mMaxMessageTimestamp = Long.MIN_VALUE;
    private long mMinMessageTimestamp = Long.MAX_VALUE;

    public ConversationAdapter() {
        list = new ArrayList<>();
    }

    @Override
    public ConversationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ConversationViewHolder conversationViewHolder = null;
        switch (viewType) {
            case RECEIVE:
                conversationViewHolder = new ConversationViewHolder(parent, R.layout.view_conversation_receive_item, RECEIVE);
                break;
            case SEND:
                conversationViewHolder = new ConversationViewHolder(parent, R.layout.view_conversation_send_item, SEND);
                break;
            case DATE:
                conversationViewHolder = new ConversationViewHolder(parent, R.layout.view_conversation_date_item, DATE);
                break;
        }
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
        if (item.isPast()) {
            list.add(0, item);
            item.setInnerType(INIT);
            int position = 0;
            if (list.size() > 1) {
                int currentType = item.getType();
                int afterType = list.get(position + 1).getType();

                if (currentType == afterType) {
                    list.get(position + 1).setSingle(false);
                    list.get(position + 1).setInnerType(FINAL);
                    item.setSingle(false);
                    if ((list.size() > 2) && (list.get(position + 2).getInnerType() != INIT))
                        list.get(position + 1).setInnerType(MID);
                }
            }
        } else {
            list.add(item);
            item.setInnerType(INIT);
            int position = list.size() - 1;
            if (position > 0) {
                int currentType = item.getType();
                int beforeType = list.get(position - 1).getType();

                if (currentType == beforeType) {
                    list.get(position - 1).setSingle(false);
                    item.setSingle(false);
                    item.setInnerType(FINAL);
                    if (list.get(position - 1).getInnerType() == FINAL)
                        list.get(position - 1).setInnerType(MID);
                }
            }
        }

        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        ConversationItem conversationItem = list.get(position);
        return conversationItem.getType();
    }

    public List<ConversationItem> getList() {
        return list;
    }

    public long getMaxMessageTimestamp() {
        return mMaxMessageTimestamp == Long.MIN_VALUE ? Long.MAX_VALUE : mMaxMessageTimestamp;
    }
}
