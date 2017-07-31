package com.tuzhi.application;

import android.app.Fragment;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.view.WindowCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioGroup;

import com.tuzhi.application.databinding.ActivityMainBinding;
import com.tuzhi.application.moudle.message.mvp.MessageFragment;
import com.tuzhi.application.moudle.mine.mvp.MineFragment;
import com.tuzhi.application.moudle.repository.mvp.RepositoryFragment;
import com.tuzhi.application.moudle.search.mvp.SearchFragment;
import com.tuzhi.application.utils.ToastUtilsKt;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private int oldCheckedId = 0;
    private long currentTime;
    private Map<Integer, Fragment> fragmentMap = new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(WindowCompat.FEATURE_ACTION_MODE_OVERLAY);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.rg.setOnCheckedChangeListener(this);
        fragmentMap.put(R.id.rbHomePage, new RepositoryFragment());
        fragmentMap.put(R.id.rbSearch, new SearchFragment());
        fragmentMap.put(R.id.rbMessage, new MessageFragment());
        fragmentMap.put(R.id.rbMine, new MineFragment());
        binding.rbHomePage.setChecked(true);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        Fragment oldFragment = fragmentMap.get(oldCheckedId);
        Fragment fragmentById = getFragmentManager().findFragmentByTag(checkedId + "");
        if (fragmentById == null) {
            getFragmentManager().beginTransaction().add(R.id.fl, fragmentMap.get(checkedId), checkedId + "").commit();
        } else {
            getFragmentManager().beginTransaction().show(fragmentById).commit();
        }

        if (oldCheckedId != 0) {
            getFragmentManager().beginTransaction().hide(oldFragment).commit();
        }
        oldCheckedId = checkedId;
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
}
