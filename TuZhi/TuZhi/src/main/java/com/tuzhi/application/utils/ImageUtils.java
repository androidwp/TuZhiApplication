package com.tuzhi.application.utils;

import android.databinding.BindingAdapter;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.tuzhi.application.R;

/**
 * Created by wangpeng on 2017/6/5.
 */

public class ImageUtils {
    @BindingAdapter("imageLoad")
    public static void loadImage(ImageView iv, String url) {
        Glide.with(iv.getContext()).load(url).diskCacheStrategy(DiskCacheStrategy.RESULT).into(iv);
    }

    public static void loadImage(ImageView iv, String url, int drawableId) {
        if (TextUtils.isEmpty(url)) {
            iv.setImageResource(drawableId);
        } else
            Glide.with(iv.getContext()).load(url).diskCacheStrategy(DiskCacheStrategy.RESULT).into(iv).onLoadFailed(null, CommonUtils.getDrawable(iv.getContext(), drawableId));
    }

    public static int getFileImage(String fileName, int type) {
        int drawableId;
        if (TextUtils.isEmpty(fileName)) {
            if (type == 0)
                drawableId = R.drawable.unknown;
            else {
                drawableId = R.drawable.unknownbig;
            }
        } else {
            if ("gif/jpg/png/jpeg".contains(fileName)) {
                if (type == 0)
                    drawableId = R.drawable.pic;
                else {
                    drawableId = R.drawable.picbig;
                }
            } else if ("doc/docx".contains(fileName)) {
                if (type == 0)
                    drawableId = R.drawable.word;
                else {
                    drawableId = R.drawable.wordbig;
                }
            } else if ("ppttx/ppt".contains(fileName)) {
                if (type == 0)
                    drawableId = R.drawable.ppt;
                else {
                    drawableId = R.drawable.pptbig;
                }
            } else if ("xls/xlsx".contains(fileName)) {
                if (type == 0)
                    drawableId = R.drawable.excel;
                else {
                    drawableId = R.drawable.excelbig;
                }
            } else if ("pdf".contains(fileName)) {
                if (type == 0)
                    drawableId = R.drawable.pdf;
                else {
                    drawableId = R.drawable.pdfbig;
                }
            } else if ("html".contains(fileName)) {
                if (type == 0)
                    drawableId = R.drawable.html;
                else {
                    drawableId = R.drawable.htmlbig;
                }
            } else if ("txt".contains(fileName)) {
                if (type == 0)
                    drawableId = R.drawable.txt;
                else {
                    drawableId = R.drawable.txtbig;
                }
            } else if ("mp4/avi/rmvb/mkv".contains(fileName)) {
                if (type == 0)
                    drawableId = R.drawable.video;
                else {
                    drawableId = R.drawable.videobig;
                }
            } else if ("zip/rar/7z/cab/jar/iso/tar".contains(fileName)) {
                if (type == 0)
                    drawableId = R.drawable.mpackage;
                else {
                    drawableId = R.drawable.packagebig;
                }
            } else {
                drawableId = R.drawable.unknown;
            }
        }
        return drawableId;
    }
}
