package com.example.user.bikeechatting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.user.bikeechatting.dto.ReceiveObject;
import com.example.user.bikeechatting.manager.NetworkManager;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MAIN_ACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // email : cuser1@naver.com, password : 1234
        // email : cuser2@naver.com, password : 1234
        // email : cuser3@naver.com, password : 1234
        // email : cuser4@naver.com, password : 1234
        // email : cuser5@naver.com, password : 1234
        // email : cuser6@naver.com, password : 1234
        // email : cuser7@naver.com, password : 1234
        // email : cuser8@naver.com, password : 1234
        // email : cuser9@naver.com, password : 1234
        NetworkManager.getInstance().login(
                "cuser1@naver.com",
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
    }
}