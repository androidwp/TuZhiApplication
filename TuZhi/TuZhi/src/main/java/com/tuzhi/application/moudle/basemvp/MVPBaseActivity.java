package com.tuzhi.application.moudle.basemvp;

import android.app.ProgressDialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.WindowCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.tuzhi.application.utils.DarkUtils;
import com.umeng.analytics.MobclickAgent;

import java.lang.reflect.ParameterizedType;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public abstract class MVPBaseActivity<V extends BaseView, T extends BasePresenterImpl<V>> extends AppCompatActivity implements BaseView {
    public T mPresenter;
    protected ProgressDialog progressDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(WindowCompat.FEATURE_ACTION_MODE_OVERLAY);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            DarkUtils.setStatusBarIconDark(this, true);
            DarkUtils.setStatusBarDarkMode(this, true);
        }
        mPresenter = getInstance(this, 1);
        mPresenter.attachView((V) this);
        progressDialog = new ProgressDialog(this);
        init(DataBindingUtil.setContentView(this, getLayoutId()));
    }

    protected abstract int getLayoutId();

    protected abstract void init(ViewDataBinding viewDataBinding);

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.detachView();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    public <T> T getInstance(Object o, int i) {
        try {
            return ((Class<T>) ((ParameterizedType) (o.getClass()
                    .getGenericSuperclass())).getActualTypeArguments()[i])
                    .newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassCastException e) {
            e.printStackTrace();
        }
        return null;
    }
}
