package com.tuzhi.application;

import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.view.WindowCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.alibaba.fastjson.JSONObject;
import com.tuzhi.application.bean.EventBusBean;
import com.tuzhi.application.databinding.ActivityMainBinding;
import com.tuzhi.application.moudle.message.mvp.MessageFragment;
import com.tuzhi.application.moudle.message.read.mvp.ReadFragment;
import com.tuzhi.application.moudle.mine.mvp.MineFragment;
import com.tuzhi.application.moudle.repository.mvp.RepositoryFragment;
import com.tuzhi.application.moudle.search.mvp.SearchFragment;
import com.tuzhi.application.utils.DarkUtils;
import com.tuzhi.application.utils.HttpCallBack;
import com.tuzhi.application.utils.HttpUtilsKt;
import com.tuzhi.application.utils.LogUtilsKt;
import com.tuzhi.application.utils.ToastUtilsKt;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

import me.shihao.library.XRadioGroup;

public class MainActivity extends AppCompatActivity implements XRadioGroup.OnCheckedChangeListener {
    public static final String NAME = "MainActivity";
    private int oldCheckedId = 0;
    private long currentTime;
    private Map<Integer, Fragment> fragmentMap = new HashMap<>();
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        supportRequestWindowFeature(WindowCompat.FEATURE_ACTION_MODE_OVERLAY);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            DarkUtils.setStatusBarIconDark(this, true);
            DarkUtils.setStatusBarDarkMode(this, true);
        }
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.rg.setOnCheckedChangeListener(this);
        fragmentMap.put(R.id.rbHomePage, new RepositoryFragment());
        fragmentMap.put(R.id.rbSearch, new SearchFragment());
        fragmentMap.put(R.id.rbMessage, new MessageFragment());
        fragmentMap.put(R.id.rbMine, new MineFragment());
        binding.rbHomePage.setChecked(true);
        checkUnreadMessage();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    //接收消息
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMain(EventBusBean busBean) {
        if (busBean.getName().equals(NAME))
            binding.bv.setBadgeCount(busBean.getiContent());
    }

    @Override
    public void onBackPressed() {
        long timeMillis = System.currentTimeMillis();
        final int anInt = 2000;
        if (timeMillis - currentTime < anInt) {
            super.onBackPressed();
        } else {
            currentTime = timeMillis;
            ToastUtilsKt.toast(this, "再次点击退出应用");
        }
    }

    @Override
    public void onCheckedChanged(XRadioGroup xRadioGroup, @IdRes int checkedId) {
        Fragment oldFragment = fragmentMap.get(oldCheckedId);
        Fragment fragmentById = getSupportFragmentManager().findFragmentByTag(checkedId + "");
        if (fragmentById == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.fl, fragmentMap.get(checkedId), checkedId + "").commit();
        } else {
            getSupportFragmentManager().beginTransaction().show(fragmentById).commit();
        }

        if (oldCheckedId != 0) {
            getSupportFragmentManager().beginTransaction().hide(oldFragment).commit();
        }
        oldCheckedId = checkedId;
    }

    private void checkUnreadMessage() {
        WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(this);
        parameter.put("operate", "2");
        parameter.put("pageNo", "0");
        parameter.put("rStatus", "0");
        HttpUtilsKt.get(this, "user/notice", parameter, String.class, new HttpCallBack<String>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void onSuccess(String s, String text) {
                LogUtilsKt.showLog("消息数", text);
                JSONObject jsonObject = JSONObject.parseObject(text);
                int readCount = jsonObject.getInteger("readCount");
                if (readCount > 0) {
                    EventBus.getDefault().post(ReadFragment.REFRESH);
                    binding.bv.setBadgeCount(readCount);
                }
                if (!MainActivity.this.isDestroyed()) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            checkUnreadMessage();
                        }
                    }, 60000);
                }
            }

            @Override
            public void onFailure(String text) {

            }
        });
    }
}
