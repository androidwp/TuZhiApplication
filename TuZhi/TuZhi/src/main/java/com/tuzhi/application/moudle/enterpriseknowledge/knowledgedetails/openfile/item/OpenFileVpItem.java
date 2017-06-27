package com.tuzhi.application.moudle.enterpriseknowledge.knowledgedetails.openfile.item;

import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.tuzhi.application.R;
import com.tuzhi.application.databinding.ItemOpenFileVpBinding;
import com.tuzhi.application.item.BaseItem;

import org.jetbrains.annotations.NotNull;

/**
 * Created by wangpeng on 2017/6/26.
 */

public class OpenFileVpItem extends BaseItem<String> {

    private ItemOpenFileVpBinding binding;

    @Override
    public void bindView(@NotNull View view) {
        binding = DataBindingUtil.bind(view);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_open_file_vp;
    }

    @Override
    public void handleData(String s, int i) {
        Glide.with(context).load(s).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                Drawable drawable = new BitmapDrawable(resource);
                binding.pv.setImageDrawable(drawable);
            }
        });
    }
}
