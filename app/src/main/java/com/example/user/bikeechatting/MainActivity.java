package com.example.user.bikeechatting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.example.user.bikeechatting.chatting.ChattingRoomsFragment;
import com.example.user.bikeechatting.dto.ReceiveObject;
import com.example.user.bikeechatting.etc.SendBirdHelper;
import com.example.user.bikeechatting.manager.NetworkManager;
import com.sendbird.android.SendBird;
import com.sendbird.android.SendBirdException;
import com.sendbird.android.UserListQuery;
import com.sendbird.android.model.User;

import java.util.List;

import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MAIN_ACTIVITY";
    private ChattingRoomsFragment chattingRoomsFragment;

    final String appId = "2E377FE1-E1AD-4484-A66F-696AF1306F58"; /* Sample SendBird Application */
    String userId = SendBirdHelper.generateDeviceUUID(MainActivity.this); /* Generate Device UUID */
    String userName = "User-" + "20B5A"; /* Generate User Nickname */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_main_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setCustomView(R.layout.renter_main_toolbar);

        ButterKnife.bind(this);

        NetworkManager.getInstance().login(
                "admin@naver.com",
                "1234",
                new Callback<ReceiveObject>() {
                    @Override
                    public void success(ReceiveObject receiveObject, Response response) {
                        if (BuildConfig.DEBUG)
                            Log.d(TAG, "onResponse Success : " + receiveObject.isSuccess()
                                            + ", Code : " + receiveObject.getCode()
                                            + ", Msg : " + receiveObject.getMsg()
                            );
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        if (BuildConfig.DEBUG)
                            Log.d(TAG, "onFailure Error : " + error.toString());
                    }
                });

//        String gcmRegToken = PreferenceManager.getDefaultSharedPreferences(SendBirdUserListActivity.this).getString("SendBirdGCMToken", "");
        String gcmRegToken = "f7x_1qavNuM:APA91bGB8RVUTMtxFbTehOYO-gr5JFUORJQZDLtzAsXoDD_o2ZBqHn_PhqAfzpJwSbY6SF6iY7_mfK4nrEERZsZbq5HuddaVqKPBA6OKBdjJrSTxjEJEyfIzLcJeNpPcgoo0f66cXwxY";

        SendBird.init(this, appId);
        SendBird.login(SendBird.LoginOption.build(userId).setUserName(userName).setGCMRegToken(gcmRegToken));

        UserListQuery mUserListQuery = SendBird.queryUserList();
        mUserListQuery.setLimit(30);

        if (mUserListQuery != null && mUserListQuery.hasNext() && !mUserListQuery.isLoading()) {
            mUserListQuery.next(new UserListQuery.UserListQueryResult() {
                @Override
                public void onResult(List<User> users) {
                    for (User user : users)
                        if (BuildConfig.DEBUG)
                            Log.d(TAG, "getId : " + user.getId()
                                    + ", getImageUrl : " + user.getImageUrl()
                                    + ", getName : " + user.getName()
                            );
                }

                @Override
                public void onError(SendBirdException e) {
                }
            });
        }

        chattingRoomsFragment = ChattingRoomsFragment.newInstance(appId, userId, userName, gcmRegToken);
        if (savedInstanceState == null)
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_chatting_rooms_container, chattingRoomsFragment).commit();
    }
}