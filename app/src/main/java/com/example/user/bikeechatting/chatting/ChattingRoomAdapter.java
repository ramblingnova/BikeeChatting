package com.example.user.bikeechatting.chatting;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.bikeechatting.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 2016-03-11.
 */
public class ChattingRoomAdapter extends RecyclerView.Adapter<ChattingRoomViewHolder> {
    private List<ChattingRoomItem> list;
    private OnChattingRoomAdapterClickListener onChattingRoomAdapterClickListener;

    public ChattingRoomAdapter() {
        list = new ArrayList<>();
    }

    @Override
    public ChattingRoomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 리스트를 만들때 몇 개 생성합니다.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_chatting_room_item, parent, false);

        ChattingRoomViewHolder chattingRoomViewHolder = new ChattingRoomViewHolder(view);

        return chattingRoomViewHolder;
    }

    @Override
    public void onBindViewHolder(ChattingRoomViewHolder holder, final int position) {
        // 뷰를 돌려씁니다.
        holder.setView(list.get(position));
        holder.setOnChattingRoomClickListener(new OnChattingRoomClickListener() {
            @Override
            public void onChattingRoomClick(View view) {
                // 채팅방을 하나 클릭했을 경우에 이쪽으로 넘어오고 ChattingRoomsFragment로 넘어갑니다.
                if (onChattingRoomAdapterClickListener != null)
                    onChattingRoomAdapterClickListener.onChattingRoomAdapterClick(view, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void add(ChattingRoomItem item) {
        list.add(item);
        notifyDataSetChanged();
    }

    public void setOnChattingRoomAdapterClickListener(OnChattingRoomAdapterClickListener onChattingRoomAdapterClickListener) {
        this.onChattingRoomAdapterClickListener = onChattingRoomAdapterClickListener;
    }
}
