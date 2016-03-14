package com.example.user.bikeechatting.chatting.room;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.bikeechatting.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by User on 2016-03-14.
 */
public class ConversationViewHolder extends RecyclerView.ViewHolder {
    @Bind(R.id.view_conversation_item_user_image_image_view)
    ImageView userImage;
    @Bind(R.id.view_conversation_item_conversation_text_view)
    TextView conversation;
    @Bind(R.id.view_conversation_item_conversation_time_text_view)
    TextView conversationTime;

    public ConversationViewHolder(View view) {
        super(view);

        ButterKnife.bind(this, view);
    }

    public void setView(ConversationItem item) {
        if (item.isOpponent()) {
            // TODO : item의 isOpponent의 결과가 true인 경우 상대방으로 인식하여 뷰를 코드로 조정해야 합니다.
            conversation.setText(item.getConversation());
        } else {
            // TODO : false인 경우 자신으로 인식하여 뷰를 코드로 조정해야 합니다.
            conversation.setText(item.getConversation());
        }
    }
}
