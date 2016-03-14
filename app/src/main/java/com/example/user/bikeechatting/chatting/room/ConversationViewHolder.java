package com.example.user.bikeechatting.chatting.room;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.user.bikeechatting.R;
import com.example.user.bikeechatting.etc.MyApplication;
import com.example.user.bikeechatting.utils.ImageUtil;

import java.text.SimpleDateFormat;

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
            // userImage
            userImage.setVisibility(View.VISIBLE);
            ImageUtil.setCircleImageFromURL(MyApplication.getmContext(), item.getUserImage(), R.drawable.noneimage, 0, userImage);

            // conversation
            RelativeLayout.LayoutParams conversationParams = (RelativeLayout.LayoutParams)conversation.getLayoutParams();
            conversationParams.addRule(RelativeLayout.RIGHT_OF, R.id.view_conversation_item_user_image_image_view);
            conversationParams.addRule(RelativeLayout.END_OF, R.id.view_conversation_item_user_image_image_view);
            conversationParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, 0);
            conversationParams.addRule(RelativeLayout.ALIGN_PARENT_END, 0);
            conversationParams.setMargins(
                    MyApplication.getmContext().getResources().getDimensionPixelOffset(R.dimen.view_holder_conversation_item_conversation_text_view_left_margin),
                    0,
                    0,
                    0
            );
            conversation.setLayoutParams(conversationParams);
            conversation.setBackgroundResource(R.drawable.message_b1);
            conversation.setText(item.getConversation());

            // conversationTime
            RelativeLayout.LayoutParams conversationTimeParams = (RelativeLayout.LayoutParams)conversationTime.getLayoutParams();
            conversationTimeParams.addRule(RelativeLayout.ALIGN_LEFT, R.id.view_conversation_item_conversation_text_view);
            conversationTimeParams.addRule(RelativeLayout.ALIGN_START, R.id.view_conversation_item_conversation_text_view);
            conversationTimeParams.addRule(RelativeLayout.ALIGN_RIGHT, 0);
            conversationTimeParams.addRule(RelativeLayout.ALIGN_END, 0);
            conversationTime.setLayoutParams(conversationTimeParams);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm", java.util.Locale.getDefault());
            conversationTime.setText(simpleDateFormat.format(item.getConversationTime()));
        } else {
            // userImage
            userImage.setVisibility(View.GONE);

            // conversation
            RelativeLayout.LayoutParams conversationParams = (RelativeLayout.LayoutParams)conversation.getLayoutParams();
            conversationParams.addRule(RelativeLayout.RIGHT_OF, 0);
            conversationParams.addRule(RelativeLayout.END_OF, 0);
            conversationParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            conversationParams.addRule(RelativeLayout.ALIGN_PARENT_END);
            conversationParams.setMargins(
                    0,
                    0,
                    MyApplication.getmContext().getResources().getDimensionPixelOffset(R.dimen.view_holder_conversation_item_conversation_text_view_right_margin),
                    0
            );
            conversation.setLayoutParams(conversationParams);
            conversation.setBackgroundResource(R.drawable.message_w1);
            conversation.setText(item.getConversation());

            // conversationTime
            RelativeLayout.LayoutParams conversationTimeParams = (RelativeLayout.LayoutParams)conversationTime.getLayoutParams();
            conversationTimeParams.addRule(RelativeLayout.ALIGN_LEFT, 0);
            conversationTimeParams.addRule(RelativeLayout.ALIGN_START, 0);
            conversationTimeParams.addRule(RelativeLayout.ALIGN_RIGHT, R.id.view_conversation_item_conversation_text_view);
            conversationTimeParams.addRule(RelativeLayout.ALIGN_END, R.id.view_conversation_item_conversation_text_view);
            conversationTime.setLayoutParams(conversationTimeParams);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm", java.util.Locale.getDefault());
            conversationTime.setText(simpleDateFormat.format(item.getConversationTime()));
        }
    }
}
