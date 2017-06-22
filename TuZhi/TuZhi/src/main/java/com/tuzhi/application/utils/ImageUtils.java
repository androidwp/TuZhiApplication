package com.tuzhi.application.utils;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * Created by wangpeng on 2017/6/5.
 */

public class ImageUtils {
    @BindingAdapter("imageLoad")
    public static void loadImage(ImageView iv, String url) {
        Glide.with(iv.getContext()).load(url).diskCacheStrategy(DiskCacheStrategy.RESULT).into(iv);
    }
}
