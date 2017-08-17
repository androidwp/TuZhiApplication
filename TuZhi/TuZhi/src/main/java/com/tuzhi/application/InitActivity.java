package com.tuzhi.application;

import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.WindowCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;

import com.tuzhi.application.bean.HttpInitBean;
import com.tuzhi.application.moudle.login.mvp.LoginActivity;
import com.tuzhi.application.utils.ActivitySkipUtilsKt;
import com.tuzhi.application.utils.ConstantKt;
import com.tuzhi.application.utils.DarkUtils;
import com.tuzhi.application.utils.HttpCallBack;
import com.tuzhi.application.utils.HttpUtilsKt;
import com.tuzhi.application.utils.SharedPreferencesUtilsKt;
import com.tuzhi.application.utils.UserInfoUtils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.WeakHashMap;

public class InitActivity extends AppCompatActivity {

    private final String URL = "app/init";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(WindowCompat.FEATURE_ACTION_MODE_OVERLAY);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            DarkUtils.setStatusBarIconDark(this, true);
            DarkUtils.setStatusBarDarkMode(this, true);
        }
        DataBindingUtil.setContentView(this, R.layout.activity_init);
        //初始化
        init();
    }

    private void init() {
        WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(getApplicationContext());
        String imsi = SharedPreferencesUtilsKt.getLongCache(this, ConstantKt.getIMSI());
        String imei = SharedPreferencesUtilsKt.getLongCache(this, ConstantKt.getIMEI());
        if (!TextUtils.isEmpty(imsi))
            parameter.put("imsi", imsi);
        if (!TextUtils.isEmpty(imei))
            parameter.put("imei", imei);
        parameter.put("systemVersion", ConstantKt.getOSVERSION());
        parameter.put("phoneModel", ConstantKt.getMODEL());
        parameter.put("manufacturer", ConstantKt.getBRAND());
        parameter.put("installVersion", SharedPreferencesUtilsKt.getLongCache(this, ConstantKt.getVERSION_NAME()));
        parameter.put("installUID", SharedPreferencesUtilsKt.getLongCache(this, ConstantKt.getINSTALL_UID()));
        HttpUtilsKt.post(this, URL, parameter, HttpInitBean.class, new HttpCallBack<HttpInitBean>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void onSuccess(@Nullable HttpInitBean httpInitBean, @NotNull String text) {
                UserInfoUtils.saveUserInfo(InitActivity.this, text, httpInitBean);
                if (TextUtils.equals(httpInitBean.isLoginStatus(), "false"))
                    ActivitySkipUtilsKt.toActivity(InitActivity.this, LoginActivity.class);
                else
                    ActivitySkipUtilsKt.toActivity(InitActivity.this, MainActivity.class);
                InitActivity.this.onBackPressed();
            }

            @Override
            public void onFailure(@NotNull String text) {
                if (TextUtils.equals(text, "帐号状态异常")) {
                    SharedPreferencesUtilsKt.saveLongCache(InitActivity.this, ConstantKt.getUSER_TYPE(), "1");
                    SharedPreferencesUtilsKt.saveLongCache(InitActivity.this, ConstantKt.getUSER_ID(), "");
                }
                init();
            }
        });
    }
}
