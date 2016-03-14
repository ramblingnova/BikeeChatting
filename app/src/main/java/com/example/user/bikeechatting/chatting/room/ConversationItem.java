package com.example.user.bikeechatting.chatting.room;

import java.util.Date;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by User on 2016-03-14.
 */
public class ConversationItem {
    @Getter
    @Setter(AccessLevel.PUBLIC)
    String userImage;
    @Getter
    @Setter(AccessLevel.PUBLIC)
    String conversation;
    @Getter
    @Setter(AccessLevel.PUBLIC)
    Date conversationTime;
    @Getter
    @Setter(AccessLevel.PUBLIC)
    boolean opponent;

    public ConversationItem(String userImage, String conversation, Date conversationTime, boolean opponent) {
        this.userImage = userImage;
        this.conversation = conversation;
        this.conversationTime = conversationTime;
        this.opponent = opponent;
    }
}