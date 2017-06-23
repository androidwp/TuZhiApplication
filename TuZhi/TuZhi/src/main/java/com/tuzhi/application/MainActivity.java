package com.tuzhi.application;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.tuzhi.application.bean.HttpInitBean;
import com.tuzhi.application.databinding.ActivityMainBinding;
import com.tuzhi.application.moudle.login.mvp.LoginActivity;
import com.tuzhi.application.moudle.repository.mvp.RepositoryActivity;
import com.tuzhi.application.utils.ActivitySkipUtilsKt;
import com.tuzhi.application.utils.ConstantKt;
import com.tuzhi.application.utils.HttpCallBack;
import com.tuzhi.application.utils.HttpUtilsKt;
import com.tuzhi.application.utils.SharedPreferencesUtilsKt;
import com.tuzhi.application.utils.UserInfoUtils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.WeakHashMap;

public class MainActivity extends AppCompatActivity {

    private final String URL = "app/init";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
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
                UserInfoUtils.saveUserInfo(MainActivity.this, text, httpInitBean);
                if (TextUtils.equals(httpInitBean.isLoginStatus(), "false"))
                    ActivitySkipUtilsKt.toActivity(MainActivity.this, LoginActivity.class);
                else
                    ActivitySkipUtilsKt.toActivity(MainActivity.this, RepositoryActivity.class);
                MainActivity.this.onBackPressed();
            }

            @Override
            public void onFailure(@NotNull String text) {

            }
        });
    }
}
