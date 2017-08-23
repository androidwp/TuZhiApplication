package com.tuzhi.application.moudle.repository.crepository.mvp;


import android.databinding.ViewDataBinding;
import android.text.TextUtils;

import com.tuzhi.application.R;
import com.tuzhi.application.databinding.ActivityCrepositoryBinding;
import com.tuzhi.application.moudle.basemvp.MVPBaseActivity;
import com.tuzhi.application.moudle.repository.crepository.bean.CreateRepositoryBean;
import com.tuzhi.application.moudle.repository.knowledgachannel.mvp.KnowledgeChannelActivity;
import com.tuzhi.application.moudle.repository.mvp.RepositoryFragment;
import com.tuzhi.application.utils.ConstantKt;
import com.tuzhi.application.utils.KeyBoardUtils;

import org.greenrobot.eventbus.EventBus;


/**
 * 创建知识库页面
 */

public class CrepositoryActivity extends MVPBaseActivity<CrepositoryContract.View, CrepositoryPresenter> implements CrepositoryContract.View {

    //用于区分知识库和知识模块，repository为知识库，moudle为知识模块
    public static final String TYPE = "TYPE";
    public static final String REPOSITORY = "repository";
    public static final String MOUDLE = "moudle";
    public static final String CHANNEL = "CHANNEL";
    public static final String ID = "ID";
    private String id;
    private String type;
    private boolean canClick = true;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_crepository;
    }

    @Override
    protected void init(ViewDataBinding viewDataBinding) {
        ActivityCrepositoryBinding binding = (ActivityCrepositoryBinding) viewDataBinding;
        binding.setActivity(this);
        type = getIntent().getStringExtra(TYPE);
        id = getIntent().getStringExtra(ID);
        switch (type) {
            case REPOSITORY:
                binding.setData(new CreateRepositoryBean(type, "新建知识库", "请输入知识库名称", ""));
                break;
            case MOUDLE:
                binding.setData(new CreateRepositoryBean(type, "新建卡片", "请输入卡片名称", ""));
                break;
            case CHANNEL:
                binding.setData(new CreateRepositoryBean(type, "新建频道", "请输入频道名称", ""));
                break;
        }
        KeyBoardUtils.showKeyBoard(this);
    }

    public void cancel() {
        onBackPressed();
    }

    public void commit(String type, String name) {
        if (canClick) {
            canClick = false;
            mPresenter.commit(type, name, id);
        }
    }



    @Override
    public void commitFinish() {
        if (TextUtils.equals(type, CHANNEL)) {
            EventBus.getDefault().post(RepositoryFragment.MESSAGE);
        } else if (TextUtils.equals(type, MOUDLE)) {
            EventBus.getDefault().post(KnowledgeChannelActivity.MESSAGE);
        }
        setResult(ConstantKt.getCREATE_CODE());
        onBackPressed();
    }

    @Override
    public void commitError() {
        canClick = true;
    }
}
