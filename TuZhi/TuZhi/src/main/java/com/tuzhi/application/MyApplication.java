package com.tuzhi.application;

import android.Manifest;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import com.alibaba.fastjson.JSONObject;
import com.tencent.smtt.sdk.QbSdk;
import com.tuzhi.application.bean.HttpInitBean;
import com.tuzhi.application.service.ClipboardService;
import com.tuzhi.application.utils.ConstantKt;
import com.tuzhi.application.utils.GlideImageLoader;
import com.tuzhi.application.utils.LogUtilsKt;
import com.tuzhi.application.utils.SharedPreferencesUtilsKt;
import com.yanzhenjie.album.Album;
import com.yanzhenjie.album.AlbumConfig;

import java.util.Locale;

/**
 * Created by wangpeng on 2017/6/20.
 */

public class MyApplication extends Application {

    // 创建服务用于捕获崩溃异常
    private Thread.UncaughtExceptionHandler restartHandler = new Thread.UncaughtExceptionHandler() {
        public void uncaughtException(Thread thread, Throwable ex) {
            LogUtilsKt.showLog("bug",ex.getLocalizedMessage());
            restartApp();//发生崩溃异常时,重启应用
        }
    };

    public void restartApp() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        android.os.Process.killProcess(android.os.Process.myPid());  //结束进程之前可以把你程序的注销或者退出代码放在这段代码之前
    }


    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
       // Thread.setDefaultUncaughtExceptionHandler(restartHandler);
        getVersion();
        getImieStatus();
        initLogin();
        QbSdk.initX5Environment(this, null);
        initAlbum();
        startService(new Intent(this, ClipboardService.class));
    }

    private void initAlbum() {
        Album.initialize(AlbumConfig.newBuilder(this)
                .setImageLoader(new GlideImageLoader()) // Use glide loader.
                .setLocale(Locale.CHINA)
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
