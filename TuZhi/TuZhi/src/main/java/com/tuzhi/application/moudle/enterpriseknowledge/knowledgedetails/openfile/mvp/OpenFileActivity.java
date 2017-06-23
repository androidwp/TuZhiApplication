package com.tuzhi.application.moudle.enterpriseknowledge.knowledgedetails.openfile.mvp;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.databinding.ViewDataBinding;

import com.tuzhi.application.R;
import com.tuzhi.application.databinding.ActivityOpenFileBinding;
import com.tuzhi.application.moudle.basemvp.MVPBaseActivity;
import com.tuzhi.application.moudle.enterpriseknowledge.knowledgedetails.openfile.fragment.NotOpenFileFragment;
import com.tuzhi.application.moudle.enterpriseknowledge.knowledgedetails.openfile.fragment.OpenFileFragment;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class OpenFileActivity extends MVPBaseActivity<OpenFileContract.View, OpenFilePresenter> implements OpenFileContract.View {

    public static final String TITLE = "TITLE";
    //告知文件可否展示
    public static final String TYPE = "TYPE";
    public static final String TYPE_CAN_OPEN = "TYPE_CAN_OPEN";
    public static final String TYPE_NOT_OPEN = "TYPE_NOT_OPEN";


    @Override
    protected int getLayoutId() {
        return R.layout.activity_open_file;
    }

    @Override
    protected void init(ViewDataBinding viewDataBinding) {
        String title = getIntent().getStringExtra(TITLE);
        String type = getIntent().getStringExtra(TYPE);
        ActivityOpenFileBinding binding = (ActivityOpenFileBinding) viewDataBinding;
        binding.setActivity(this);
        binding.setTitle(title);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (type.equals(TYPE_CAN_OPEN)) {
            fragmentTransaction.add(R.id.fl, new OpenFileFragment());
        } else {
            fragmentTransaction.add(R.id.fl, new NotOpenFileFragment());
        }

    }

    public void back() {
        onBackPressed();
    }
}
