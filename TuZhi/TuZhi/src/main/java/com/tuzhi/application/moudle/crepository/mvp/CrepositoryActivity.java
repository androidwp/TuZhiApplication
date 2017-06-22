package com.tuzhi.application.moudle.crepository.mvp;


import android.databinding.ViewDataBinding;

import com.tuzhi.application.R;
import com.tuzhi.application.databinding.ActivityCrepositoryBinding;
import com.tuzhi.application.moudle.basemvp.MVPBaseActivity;
import com.tuzhi.application.moudle.crepository.bean.CreateRepositoryBean;
import com.tuzhi.application.utils.ConstantKt;


/**
 * 创建知识库页面
 */

public class CrepositoryActivity extends MVPBaseActivity<CrepositoryContract.View, CrepositoryPresenter> implements CrepositoryContract.View {

    //用于区分知识库和知识模块，repository为知识库，moudle为知识模块
    public static final String TYPE = "TYPE";
    public static final String REPOSITORY = "repository";
    public static final String MOUDLE = "moudle";
    public static final String LibId = "LibId";
    private String libID;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_crepository;
    }

    @Override
    protected void init(ViewDataBinding viewDataBinding) {
        ActivityCrepositoryBinding binding = (ActivityCrepositoryBinding) viewDataBinding;
        binding.setActivity(this);
        String type = getIntent().getStringExtra(TYPE);
        libID = getIntent().getStringExtra(LibId);
        switch (type) {
            case REPOSITORY:
                binding.setData(new CreateRepositoryBean(type, "新建知识库", "请输入知识库名称", ""));
                break;
            case MOUDLE:
                binding.setData(new CreateRepositoryBean(type, "新建知识模块", "请输入知识模块名称", ""));
                break;
        }
    }

    public void cancel() {
        onBackPressed();
    }

    public void commit(String type, String name) {
        mPresenter.commit(type, name, libID);
    }

    @Override
    public void commitFinish() {
        setResult(ConstantKt.getCREATE_CODE());
        onBackPressed();
    }
}
