package com.example.user.bikeechatting.chatting.room;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.user.bikeechatting.R;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ConversationActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    @Bind(R.id.conversation_toolbar_user_name_text_view)
    TextView userName;
    @Bind(R.id.activity_conversation_swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.activity_conversation_recycler_view)
    RecyclerView recyclerView;
    private ConversationAdapter conversationAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);

        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_conversation_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setCustomView(R.layout.conversation_toolbar);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        userName.setText(intent.getStringExtra("userName"));

        swipeRefreshLayout.setOnRefreshListener(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        conversationAdapter = new ConversationAdapter();
        init();
        recyclerView.setAdapter(conversationAdapter);

        recyclerView.addItemDecoration(
                new ConversationDecoration(
                        getResources().getDimensionPixelSize(R.dimen.view_holder_conversation_item_space)
                )
        );
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
            }
        }, 2000);
    }

    @OnClick(R.id.conversation_toolbar_back_button_layout)
    void back(View view) {
        super.onBackPressed();
    }

    private void init() {
        for (int i = 0; i < 30; i++) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", java.util.Locale.getDefault());
            Date conversationTime = new Date();
            conversationTime.setTime(System.currentTimeMillis());
            try {
                conversationTime = format.parse(conversationTime.toString().replaceAll("Z$", "+0000"));
            } catch (Exception e) {
                e.printStackTrace();
            }
            boolean opponent = (i % 2) == 0;
            conversationAdapter.add(
                    new ConversationItem(
                            "",
                            "conversation" + i,
                            conversationTime,
                            opponent
                    )
            );
        }
    }
}
