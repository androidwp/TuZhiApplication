package com.tuzhi.application;

import android.Manifest;
import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import com.alibaba.fastjson.JSONObject;
import com.tencent.smtt.sdk.QbSdk;
import com.tuzhi.application.bean.HttpInitBean;
import com.tuzhi.application.utils.ConstantKt;
import com.tuzhi.application.utils.GlideImageLoader;
import com.tuzhi.application.utils.SharedPreferencesUtilsKt;
import com.yanzhenjie.album.Album;
import com.yanzhenjie.album.AlbumConfig;

/**
 * Created by wangpeng on 2017/6/20.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        getVersion();
        getImieStatus();
        initLogin();
        QbSdk.initX5Environment(this, null);
        Album.initialize(AlbumConfig.newBuilder(this)
                .setImageLoader(new GlideImageLoader()) // Use glide loader.
                .build());
    }

    private void initLogin() {
        String user_information = SharedPreferencesUtilsKt.getLongCache(this, ConstantKt.getUSER_INFO());
        if (!TextUtils.isEmpty(user_information)) {
            try {
                HttpInitBean httpUserBean = JSONObject.parseObject(user_information, HttpInitBean.class);
                SharedPreferencesUtilsKt.saveLongCache(this, ConstantKt.getUSER_TYPE(), httpUserBean.getUserType());
                SharedPreferencesUtilsKt.saveLongCache(this, ConstantKt.getUSER_ID(), httpUserBean.getUserId());
            } catch (Exception e) {
                SharedPreferencesUtilsKt.saveLongCache(this, ConstantKt.getUSER_TYPE(), "1");
            }
        } else {
            SharedPreferencesUtilsKt.saveLongCache(this, ConstantKt.getUSER_TYPE(), "1");
        }
    }

    private void getVersion() {
        PackageManager packageManager = getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(getPackageName(), 0);
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
            String versionName = packageInfo.versionName;
            String versionCode = packageInfo.versionCode + "";
            String channel = applicationInfo.metaData.getString("UMENG_CHANNEL");
            SharedPreferencesUtilsKt.saveLongCache(this, ConstantKt.getVERSION_NAME(), versionName);
            SharedPreferencesUtilsKt.saveLongCache(this, ConstantKt.getVERSION_CODE(), versionCode);
            SharedPreferencesUtilsKt.saveLongCache(this, ConstantKt.getUMENG_CHANNEL(), channel);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void getImieStatus() {
        String installUID = SharedPreferencesUtilsKt.getLongCache(this, ConstantKt.getINSTALL_UID());
        if (TextUtils.isEmpty(installUID)) {
            installUID = String.valueOf(System.currentTimeMillis());
            SharedPreferencesUtilsKt.saveLongCache(this, ConstantKt.getINSTALL_UID(), installUID);
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
            TelephonyManager tm = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
            SharedPreferencesUtilsKt.saveLongCache(this, ConstantKt.getIMSI(), tm.getSubscriberId());
            SharedPreferencesUtilsKt.saveLongCache(this, ConstantKt.getIMEI(), tm.getDeviceId());
        }
    }
}
