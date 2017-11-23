package com.tuzhi.application.moudle.createknowledgelib;


import android.databinding.ViewDataBinding;
import android.text.TextUtils;
import android.view.View;

import com.tuzhi.application.R;
import com.tuzhi.application.bean.EventBusBean;
import com.tuzhi.application.bean.ItemBean;
import com.tuzhi.application.databinding.ActivityCreateKnowledgeLibBinding;
import com.tuzhi.application.dialog.ChooseOpennessDialog;
import com.tuzhi.application.inter.OnDialogClickListener;
import com.tuzhi.application.moudle.basemvp.MVPBaseActivity;
import com.tuzhi.application.moudle.chooselibicon.ChooseLibIconActivity;
import com.tuzhi.application.moudle.chooselibtype.ChooseLibTypeActivity;
import com.tuzhi.application.moudle.repository.knowledgachannel.mvp.KnowledgeChannelActivity;
import com.tuzhi.application.moudle.repository.mvp.RepositoryFragment;
import com.tuzhi.application.utils.ActivitySkipUtilsKt;
import com.tuzhi.application.utils.ToastUtilsKt;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 *
 * @author wangpeng
 */

public class CreateKnowledgeLibActivity extends MVPBaseActivity<CreateKnowledgeLibContract.View, CreateKnowledgeLibPresenter> implements CreateKnowledgeLibContract.View, OnDialogClickListener {

    public static final String name = "CreateKnowledgeLibActivity";

    public static final String TYPE = "type";

    public static final String TYPE_CREATE = "TYPE_CREATE";

    public static final String TYPE_SET = "TYPE_SET";

    public static final String ID = "ID";

    public static final String TITLE = "TITLE";

    public static final String OPENNESS = "OPENNESS";

    public static final String CLASSIFICATION = "CLASSIFICATION";

    public static final String IMAGE = "IMAGE";

    public static int EVENT_TYPE_LIB_TYPE = 0;

    public static int EVENT_TYPE_LIB_ICON = 1;

    private CreateKnowledgeLibBean bean;

    private String[] titles = {"公共知识库", "部门知识库", "项目知识库"};
    private String type;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_create_knowledge_lib;
    }

    @Override
    protected void init(ViewDataBinding viewDataBinding) {
        EventBus.getDefault().register(this);
        ActivityCreateKnowledgeLibBinding binding = (ActivityCreateKnowledgeLibBinding) viewDataBinding;
        bean = new CreateKnowledgeLibBean();
        type = getIntent().getStringExtra(TYPE);
        if (TextUtils.equals(type, TYPE_SET)) {
            bean.setLibId(getIntent().getStringExtra(ID));
            bean.setLibName(getIntent().getStringExtra(TITLE));
            bean.setLibIcon(getIntent().getStringExtra(IMAGE));
            bean.setLibTypeId(getIntent().getStringExtra(CLASSIFICATION));
            bean.setLibTypeName(titles[Integer.parseInt(bean.getLibTypeId()) - 1]);
            bean.setLibOpenness(!getIntent().getBooleanExtra(OPENNESS, true));
        }
        binding.setActivity(this);
        binding.setData(bean);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMainEvent(EventBusBean busBean) {
        if (busBean.getName().equals(name)) {
            if (busBean.getEventType() == EVENT_TYPE_LIB_TYPE) {
                ItemBean itemBean = (ItemBean) busBean.getObject();
                bean.setLibTypeId(itemBean.getId());
                bean.setLibTypeName(itemBean.getTitle());
            } else if (busBean.getEventType() == EVENT_TYPE_LIB_ICON) {
                String image = busBean.getsContent();
                bean.setLibIcon(image);
            }

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void back() {
        onBackPressed();
    }


    public void commit() {
        mPresenter.createLib(type, bean);
    }

    public void chooseLibIcon() {
        ActivitySkipUtilsKt.toActivity(this, ChooseLibIconActivity.class);
    }

    public void chooseOpenOrSecret() {
        ChooseOpennessDialog dialog = new ChooseOpennessDialog(this, bean.isLibOpenness(), 0);
        dialog.setClickListener(this);
        dialog.show();
    }

    public void chooseLibType() {
        ActivitySkipUtilsKt.toActivity(this, ChooseLibTypeActivity.class, ChooseLibTypeActivity.ID, bean.getLibTypeId());
    }

    @Override
    public void onDialogClick(View view) {
        switch (view.getId()) {
            case R.id.flOpen:
                bean.setLibOpenness(true);
                break;
            case R.id.flSecret:
                bean.setLibOpenness(false);
                break;
            default:
                break;
        }
    }

    @Override
    public void createLibSuccess() {
        EventBus.getDefault().post(RepositoryFragment.MESSAGE);
        EventBus.getDefault().post(KnowledgeChannelActivity.MESSAGE);
        if (type.equals(TYPE_SET)) {
            ToastUtilsKt.toast(this, "修改成功");
        } else {
            ToastUtilsKt.toast(this, "创建成功");
        }
        back();
    }
}
