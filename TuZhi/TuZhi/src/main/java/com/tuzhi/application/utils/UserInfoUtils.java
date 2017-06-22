package com.tuzhi.application.utils;

import android.content.Context;

import com.alibaba.fastjson.JSONObject;
import com.tuzhi.application.bean.HttpInitBean;

/**
 * Created by wangpeng on 2017/6/22.
 */

public class UserInfoUtils {

    public static void changeUserInfo(Context context, String key, String value) {
        String userInfo = SharedPreferencesUtilsKt.getLongCache(context, ConstantKt.getUSER_INFO());
        JSONObject jsonObject = JSONObject.parseObject(userInfo);
        jsonObject.put(key, value);
        String jsonString = jsonObject.toJSONString();
        SharedPreferencesUtilsKt.saveLongCache(context, ConstantKt.getUSER_INFO(), jsonString);
    }

    public static void saveUserInfo(Context context, String text, HttpInitBean initBean) {
        SharedPreferencesUtilsKt.saveLongCache(context, ConstantKt.getUSER_INFO(), text);
        SharedPreferencesUtilsKt.saveLongCache(context, ConstantKt.getUSER_TYPE(), initBean.getUserType());
        SharedPreferencesUtilsKt.saveLongCache(context, ConstantKt.getUSER_ID(), initBean.getUserId());
    }

    public static HttpInitBean getUserInfo(Context context) {
        String userInfo = SharedPreferencesUtilsKt.getLongCache(context, ConstantKt.getUSER_INFO());
        return JSONObject.parseObject(userInfo, HttpInitBean.class);
    }
}
