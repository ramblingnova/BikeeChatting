package com.example.user.bikeechatting.etc;

import android.app.Application;
import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.MemoryCategory;

/**
 * Created by User on 2016-03-14.
 */
public class MyApplication extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        Glide.get(mContext).setMemoryCategory(MemoryCategory.HIGH);
    }

    public static Context getmContext() {
        return mContext;
    }
}
