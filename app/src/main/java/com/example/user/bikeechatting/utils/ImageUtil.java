package com.example.user.bikeechatting.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

/**
 * Created by User on 2015-11-10.
 */
public class ImageUtil {
    public static void setCircleImageFromURL(
            final Context context,
            String imageURL,
            int placeHolderResourceId,
            int imageSize,
            final ImageView targetVIew) {
        Glide.with(context)
                .load(imageURL)
                .asBitmap()
                .placeholder(placeHolderResourceId)
                .fitCenter()
                .thumbnail(0.0001f)
                .into(new BitmapImageViewTarget(targetVIew) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        targetVIew.setImageDrawable(circularBitmapDrawable);
                    }
                });
    }

    public static void setRoundRectangleImageFromURL(
            final Context context,
            String imageURL,
            int placeHolderResourceId,
            final ImageView targetView,
            final float radius) {
        Glide.with(context)
                .load(imageURL)
                .asBitmap()
                .placeholder(placeHolderResourceId)
                .fitCenter()
                .thumbnail(0.0001f)
                .into(new BitmapImageViewTarget(targetView) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                        circularBitmapDrawable.setCornerRadius(radius);
                        targetView.setImageDrawable(circularBitmapDrawable);
                    }
                });
    }
}
