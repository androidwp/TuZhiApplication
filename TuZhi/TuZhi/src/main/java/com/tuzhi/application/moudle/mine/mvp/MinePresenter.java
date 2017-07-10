package com.tuzhi.application.moudle.mine.mvp;

import android.content.Context;

import com.alibaba.fastjson.JSONObject;
import com.tuzhi.application.moudle.basemvp.BasePresenterImpl;
import com.tuzhi.application.utils.ConstantKt;
import com.tuzhi.application.utils.HttpCallBack;
import com.tuzhi.application.utils.HttpUtilsKt;
import com.tuzhi.application.utils.LogUtilsKt;
import com.tuzhi.application.utils.SharedPreferencesUtilsKt;

import java.util.WeakHashMap;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class MinePresenter extends BasePresenterImpl<MineContract.View> implements MineContract.Presenter {

    private static final String URL = "logout";

    @Override
    public void logOut(final Context context) {
        WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(mView.getContext());
        HttpUtilsKt.get(mView.getContext(), URL, parameter, String.class, new HttpCallBack<String>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void onSuccess(String s, String text) {
                LogUtilsKt.showLog("TAG",text);
                JSONObject jsonObject = JSONObject.parseObject(text);
                SharedPreferencesUtilsKt.saveLongCache(context, ConstantKt.getUSER_TYPE(),jsonObject.getString("userType"));
                SharedPreferencesUtilsKt.saveLongCache(context, ConstantKt.getUSER_ID(),jsonObject.getString("userId"));
                mView.logOutSuccess();
            }

            @Override
            public void onFailure(String text) {

            }
        });
    }
}
