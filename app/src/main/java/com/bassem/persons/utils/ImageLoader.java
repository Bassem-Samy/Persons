package com.bassem.persons.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.lang.ref.WeakReference;

/**
 * Created by Bassem on 7/3/2017.
 * Helper class that is used to load images
 * it's purpose is to make the code independent of the 3rd party library used to load image, like if we decide to use picasso instead of glide, w will just have to update the usage here and not in every occurrence
 */
public class ImageLoader {
    public static void loadImage(String imageUrl, @DrawableRes int placeHolderResourceID, ImageView imageView) {
        Glide.with(imageView.getContext()).load(imageUrl).asBitmap().placeholder(placeHolderResourceID).into(imageView);
    }

    public static void loadImage(String imageUrl, @DrawableRes int placeHolderResourceID, ImageView imageView, final ImageLoaderCallbacks callbacks) {

        Glide.with(imageView.getContext()).load(imageUrl).asBitmap().placeholder(placeHolderResourceID)
                .listener(new RequestListener<String, Bitmap>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<Bitmap> target, boolean isFirstResource) {
                        if (callbacks != null) {
                            callbacks.onFail(e);
                        }
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Bitmap resource, String model, Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        if (callbacks != null) {
                            callbacks.onSuccess();
                        }
                        return false;
                    }
                })
                .into(imageView);
    }

    public interface ImageLoaderCallbacks {
        void onSuccess();

        void onFail(Exception e);
    }
}
