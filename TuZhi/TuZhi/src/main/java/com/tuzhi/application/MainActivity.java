package com.tuzhi.application;

import android.annotation.TargetApi;
import android.app.AppOpsManager;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.provider.Settings;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.view.WindowCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;

import com.alibaba.fastjson.JSONObject;
import com.tuzhi.application.bean.EventBusBean;
import com.tuzhi.application.bean.HttpInitBean;
import com.tuzhi.application.databinding.ActivityMainBinding;
import com.tuzhi.application.dialog.UpdateDialog;
import com.tuzhi.application.dialog.WarnDialog;
import com.tuzhi.application.inter.DialogMakeSureListener;
import com.tuzhi.application.moudle.message.mvp.MessageFragment;
import com.tuzhi.application.moudle.message.read.mvp.ReadFragment;
import com.tuzhi.application.moudle.mine.mvp.MineFragment;
import com.tuzhi.application.moudle.repository.mvp.RepositoryFragment;
import com.tuzhi.application.moudle.search.mvp.SearchFragment;
import com.tuzhi.application.utils.ConstantKt;
import com.tuzhi.application.utils.DarkUtils;
import com.tuzhi.application.utils.HttpCallBack;
import com.tuzhi.application.utils.HttpUtilsKt;
import com.tuzhi.application.utils.LogUtilsKt;
import com.tuzhi.application.utils.SharedPreferencesUtilsKt;
import com.tuzhi.application.utils.ToastUtilsKt;
import com.tuzhi.application.utils.UserInfoUtils;
import com.umeng.message.PushAgent;
import com.umeng.message.UTrack;
import com.yanzhenjie.permission.AndPermission;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

import me.shihao.library.XRadioGroup;

public class MainActivity extends AppCompatActivity implements XRadioGroup.OnCheckedChangeListener, DialogMakeSureListener {
    public static final String NAME = "MainActivity";
    public static final int TYPE_NOTIFICATION = 0;
    public static final int TYPE_PERMISSION = 1;

    private int oldCheckedId = 0;
    private long currentTime;
    private Map<Integer, Fragment> fragmentMap = new HashMap<>();
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndPermission.with(this).requestCode(100).permission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.SYSTEM_ALERT_WINDOW).start();
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
        checkUpdate();
        checkUnreadMessage();
        initPush();
        String firstLogin = SharedPreferencesUtilsKt.getLongCache(this, ConstantKt.getKey_IsFirstLogin());
        //为空的话就是第一次安装登录
        if (TextUtils.isEmpty(firstLogin)) {
            SharedPreferencesUtilsKt.saveLongCache(this, ConstantKt.getKey_IsFirstLogin(), ConstantKt.getValue_false());
            requestPermission();
        }
    }

    private void initPush() {
        PushAgent mPushAgent = PushAgent.getInstance(this);
        //登录绑定，用于单推
        mPushAgent.addAlias("2_" + SharedPreferencesUtilsKt.getLongCache(this, ConstantKt.getUSER_ID()), "user", new UTrack.ICallBack() {
            @Override
            public void onMessage(boolean isSuccess, String message) {
                LogUtilsKt.showLog("绑定用户", "2_" + SharedPreferencesUtilsKt.getLongCache(MainActivity.this, ConstantKt.getUSER_ID()));
            }
        });
    }

    private void showDialogForRequetPermission() {
        new WarnDialog.Builder()
                .setTitle("提示")
                .setBtnRightText("去设置")
                .setBtnLeftText("暂不设置")
                .setInfo("开启微信文章剪藏功能，需要获取系统的显示悬浮窗权限，是否立即去设置？")
                .setClickListener(this)
                .builder(this).show();

    }

    private void checkUpdate() {
        HttpInitBean userInfo = UserInfoUtils.getUserInfo(this);
        if (userInfo.isIsUpdate()) {
            UpdateDialog dialog = new UpdateDialog(this);
            dialog.setText(userInfo.getUpdateReamrk());
            dialog.setUrl(userInfo.getDownloadUrl());
            dialog.setForcedUpdate(userInfo.isForceUpdate());
            dialog.show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    //接收消息
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMain(EventBusBean busBean) {
        if (busBean.getName().equals(NAME)) {
            if (busBean.getEventType() == TYPE_NOTIFICATION) {
                binding.bv.setBadgeCount(busBean.getiContent());
            } else if (busBean.getEventType() == TYPE_PERMISSION) {
                requestPermission();
            }
        }
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

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!isFloatWindowOpAllowed(this)) {//未开启
            openOrCloseClipper(false);
        }
    }

    /**
     * 请求用户给予悬浮窗的权限
     */
    public void requestPermission() {
        if (!isFloatWindowOpAllowed(this)) {//未开启
            showDialogForRequetPermission();
        } else {
            openOrCloseClipper(true);
        }
    }

    private void openOrCloseClipper(boolean flag) {
        MineFragment fragment = (MineFragment) fragmentMap.get(R.id.rbMine);
        if (flag) {
            fragment.toggleToOn(null);
            SharedPreferencesUtilsKt.saveLongCache(this, ConstantKt.getKey_AllowClipper(), ConstantKt.getValue_true());
        } else {
            fragment.toggleToOff(null);
            SharedPreferencesUtilsKt.saveLongCache(this, ConstantKt.getKey_AllowClipper(), ConstantKt.getValue_false());
        }
    }

    /**
     * 判断悬浮窗权限
     *
     * @param context
     * @return
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static boolean isFloatWindowOpAllowed(Context context) {
        final int version = Build.VERSION.SDK_INT;
        if (version >= 19) {
            return checkOp(context, 24);  // AppOpsManager.OP_SYSTEM_ALERT_WINDOW
        } else {
            return (context.getApplicationInfo().flags & 1 << 27) == 1 << 27;
        }
    }

    public static boolean checkOp(Context context, int op) {
        final int version = Build.VERSION.SDK_INT;
        if (version >= 19) {
            AppOpsManager manager = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
            try {
                Method method = manager.getClass().getDeclaredMethod("checkOp", int.class, int.class, String.class);
                int property = (Integer) method.invoke(manager, op, Binder.getCallingUid(), context.getPackageName());
                return AppOpsManager.MODE_ALLOWED == property;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 打开权限设置界面
     */
    public void openSetting() {
        try {
            Intent localIntent = new Intent(
                    "miui.intent.action.APP_PERM_EDITOR");
            localIntent.setClassName("com.miui.securitycenter",
                    "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
            localIntent.putExtra("extra_pkgname", getPackageName());
            startActivityForResult(localIntent, 11);
        } catch (ActivityNotFoundException localActivityNotFoundException) {
            Intent intent1 = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            Uri uri = Uri.fromParts("package", getPackageName(), null);
            intent1.setData(uri);
            startActivityForResult(intent1, 11);
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 11) {
            if (!isFloatWindowOpAllowed(this)) {//未开启
//                ToastUtilsKt.toast(this, "开启悬浮窗失败");
                openOrCloseClipper(false);
            } else {
                ToastUtilsKt.toast(this, "成功开启悬浮窗");
                openOrCloseClipper(true);
            }
        } else if (requestCode == 12) {
            if (Build.VERSION.SDK_INT >= 23) {
                if (!Settings.canDrawOverlays(this)) {
                    ToastUtilsKt.toast(this, "权限授予失败,无法开启悬浮窗");
                    openOrCloseClipper(false);
                } else {
                    openOrCloseClipper(true);
                }
            }
        }

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

    @Override
    public void makeSure(Dialog dialog) {
        dialog.dismiss();
        if ("Xiaomi".equals(Build.MANUFACTURER)) {//小米手机
            openSetting();
        } else if ("Meizu".equals(Build.MANUFACTURER)) {//魅族手机
            openSetting();
        } else {//其他手机
            if (Build.VERSION.SDK_INT >= 23) {
                if (!Settings.canDrawOverlays(this)) {
                    Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                    startActivityForResult(intent, 12);
                }
            }
        }
    }
}
