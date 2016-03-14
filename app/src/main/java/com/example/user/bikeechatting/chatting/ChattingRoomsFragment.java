package com.example.user.bikeechatting.chatting;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.bikeechatting.R;

import butterknife.ButterKnife;

/**
 * Created by User on 2016-03-11.
 */
public class ChattingRoomsFragment extends Fragment implements OnChattingRoomAdapterClickListener, SwipeRefreshLayout.OnRefreshListener {
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private ChattingRoomAdapter chattingRoomAdapter;

    public static ChattingRoomsFragment newInstance() {
        return new ChattingRoomsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chatting_rooms , container, false);

        ButterKnife.bind(this, view);

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.fragment_chatting_rooms_swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this);

        recyclerView = (RecyclerView) view.findViewById(R.id.fragment_chatting_rooms_recycler_view);

        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        chattingRoomAdapter = new ChattingRoomAdapter();
        chattingRoomAdapter.setOnChattingRoomAdapterClickListener(this);
        // TODO : 방목록을 가져와 adapter에 add해야 합니다.
        recyclerView.setAdapter(chattingRoomAdapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        return view;
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onChattingRoomAdapterClick(View view, int position) {
        // TODO : 채팅방을 하나 클릭했을 경우에 대화창으로 이동해야 합니다.
    }
}
