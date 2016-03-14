package com.example.user.bikeechatting.chatting;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.bikeechatting.R;
import com.example.user.bikeechatting.etc.MyApplication;
import com.example.user.bikeechatting.utils.ImageUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by User on 2016-03-11.
 */
public class ChattingRoomViewHolder extends RecyclerView.ViewHolder {
    private OnChattingRoomClickListener onChattingRoomClickListener;
    @Bind(R.id.view_chatting_room_item_user_image_image_view)
    ImageView userImage;
    @Bind(R.id.view_chatting_room_item_reservation_state_image_view)
    ImageView reservationState;
    @Bind(R.id.view_chatting_room_item_user_name_text_view)
    TextView userName;
    @Bind(R.id.view_chatting_room_item_last_conversation_time_text_view)
    TextView lastConversationTime;
    @Bind(R.id.view_chatting_room_item_bicycle_name_text_view)
    TextView bicycleName;
    @Bind(R.id.view_chatting_room_item_last_conversation_text_view)
    TextView lastConversation;
    @Bind(R.id.view_chatting_room_item_num_of_stacked_conversation_text_view)
    TextView numOfStackedConversation;

    public ChattingRoomViewHolder(View view) {
        super(view);

        ButterKnife.bind(this, view);
    }

    public void setView(ChattingRoomItem item) {
        ImageUtil.setCircleImageFromURL(MyApplication.getmContext(), item.getUserImage(), R.drawable.noneimage, 0, userImage);
        // item의 reservationState에 따라 icon_step1 ~ 4 결정(추후에 적용하겠습니다.)
        userName.setText(item.getUserName());
        // TODO : item의 lastConversationTime을 가이드에 따른 형식으로 변환해 lastConversationTime에 setText 해주시면 됩니다.
        bicycleName.setText(item.getBicycleName());
        lastConversation.setText(item.getLastConversation());
        // TODO : item의 numOfStackedConversation가 없을 경우에 numOfStackedConversation에 View.INVISIBLE 처리가 필요합니다.
    }

    public void setOnChattingRoomClickListener(OnChattingRoomClickListener onChattingRoomClickListener) {
        this.onChattingRoomClickListener = onChattingRoomClickListener;
    }

    @OnClick(R.id.view_chatting_room_item_layout)
    void onClick(View view) {
        // 채팅 방을 하나 클릭할 경우 ChattingRoomAdapter로 넘어갑니다.
        if (onChattingRoomClickListener != null)
            onChattingRoomClickListener.onChattingRoomClick(view);
    }
}
