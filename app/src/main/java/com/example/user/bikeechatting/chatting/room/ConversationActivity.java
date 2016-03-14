package com.example.user.bikeechatting.chatting.room;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.user.bikeechatting.R;

import butterknife.ButterKnife;

public class ConversationActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);

        ButterKnife.bind(this);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.activity_conversation_swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this);

        recyclerView = (RecyclerView) findViewById(R.id.activity_conversation_recycler_view);

        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        ConversationAdapter conversationAdapter = new ConversationAdapter();
        // TODO : 방 대화 목록을 가져와 adapter에 add해야 합니다.
        recyclerView.setAdapter(conversationAdapter);

        recyclerView.addItemDecoration(
                new ConversationDecoration(
                        getResources().getDimensionPixelSize(R.dimen.view_holder_conversation_item_space)
                )
        );
    }

    @Override
    public void onRefresh() {

    }
}
