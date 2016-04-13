package com.example.user.bikeechatting;

import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.example.user.bikeechatting.chatting.ChattingRoomsFragment;
import com.example.user.bikeechatting.etc.SendBirdHelper;
import com.example.user.bikeechatting.etc.gcm.RegistrationIntentService;
import com.sendbird.android.SendBird;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MAIN_ACTIVITY";
    private ChattingRoomsFragment chattingRoomsFragment;
    /* Sample SendBird Application */
    private final String appId = "2E377FE1-E1AD-4484-A66F-696AF1306F58";
    /* Generate Device UUID */
    private String userId = SendBirdHelper.generateDeviceUUID(MainActivity.this);
    /* Generate User Nickname */
    private String userName = "Tester1";
    private String gcmRegToken;

//    String userId = "sinrim10";
//    String userId = "lklklk";
//    String userId = "Tester11";

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

        Intent intent = new Intent(this, RegistrationIntentService.class);
        startService(intent);

        gcmRegToken = PreferenceManager.getDefaultSharedPreferences(MainActivity.this).getString("SendBirdGCMToken", "");

        Log.d(TAG, "appId : " + appId
                        + "\nuserId : " + userId
                        + "\nuserName : " + userName
                        + "\ngcmRegToken : " + gcmRegToken
        );

        SendBird.init(this, appId);
        SendBird.login(
                SendBird.LoginOption.build(userId)
                        .setUserName(userName)
                        .setGCMRegToken(gcmRegToken)
        );

        /*UserListQuery mUserListQuery = SendBird.queryUserList();
        mUserListQuery.setLimit(30);

        if ((mUserListQuery != null)
                && mUserListQuery.hasNext()
                && !mUserListQuery.isLoading()) {
            mUserListQuery.next(new UserListQuery.UserListQueryResult() {
                @Override
                public void onResult(List<User> users) {
                    for (User user : users)
                        if (BuildConfig.DEBUG)
                            Log.d(TAG, "user.getId : " + user.getId()
                                            + "\nuser.getImageUrl : " + user.getImageUrl()
                                            + "\nuser.getName : " + user.getName()
                            );
                }

                @Override
                public void onError(SendBirdException e) {

                }
            });
        }*/

        chattingRoomsFragment = ChattingRoomsFragment.newInstance(appId, userId, userName, gcmRegToken);
        if (savedInstanceState == null)
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_chatting_rooms_container, chattingRoomsFragment).commit();
    }
}