package com.tuzhi.application.moudle.createchannel;


import android.databinding.ViewDataBinding;
import android.text.TextUtils;
import android.view.View;

import com.tuzhi.application.R;
import com.tuzhi.application.databinding.ActivityCreateChannelBinding;
import com.tuzhi.application.dialog.ChooseOpennessDialog;
import com.tuzhi.application.inter.OnDialogClickListener;
import com.tuzhi.application.moudle.basemvp.MVPBaseActivity;
import com.tuzhi.application.moudle.repository.enterpriseknowledge.mvp.EnterpriseKnowledgeActivity;
import com.tuzhi.application.moudle.repository.knowledgachannel.mvp.KnowledgeChannelActivity;
import com.tuzhi.application.utils.ToastUtilsKt;

import org.greenrobot.eventbus.EventBus;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 *
 * @author wangpeng
 */

public class CreateChannelActivity extends MVPBaseActivity<CreateChannelContract.View, CreateChannelPresenter> implements CreateChannelContract.View, OnDialogClickListener {

    public static final String CID = "CID";
    public static final String KID = "KID";
    public static final String TYPE = "TYPE";
    public static final String TYPE_CREATE = "2";
    public static final String TYPE_SET = "3";
    public static final String NAME = "NAME";
    public static final String SUMMARY = "SUMMARY";
    public static final String IS_OPEN = "IS_OPEN";

    private ActivityCreateChannelBinding binding;
    private CreateChannelBean bean;
    private String type;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_create_channel;
    }

    @Override
    protected void init(ViewDataBinding viewDataBinding) {
        bean = new CreateChannelBean();
        type = getIntent().getStringExtra(TYPE);
        bean.setType(type);
        bean.setkId(getIntent().getStringExtra(KID));
        if (type.equals(TYPE_SET)) {
            bean.setCId(getIntent().getStringExtra(CID));
            bean.setChannelName(getIntent().getStringExtra(NAME));
            bean.setChannelSummery(getIntent().getStringExtra(SUMMARY));
            bean.setOpenOrSecret(getIntent().getBooleanExtra(IS_OPEN, true));
        }
        binding = (ActivityCreateChannelBinding) viewDataBinding;
        binding.setActivity(this);
        binding.setData(bean);

    }

    public void back() {
        onBackPressed();
    }

    public void commit() {
        if (TextUtils.isEmpty(bean.getChannelName())) {
            ToastUtilsKt.toast(this, "请填写频道名称");
            return;
        }
        mPresenter.createChannel(bean);
    }

    public void chooseOpenOrSecret() {
        ChooseOpennessDialog dialog = new ChooseOpennessDialog(this, !bean.isOpenOrSecret(), 1);
        dialog.setClickListener(this);
        dialog.show();
    }

    @Override
    public void onDialogClick(View view) {
        switch (view.getId()) {
            case R.id.flOpen:
                bean.setOpenOrSecret(false);
                break;
            case R.id.flSecret:
                bean.setOpenOrSecret(true);
                break;
            default:
                break;
        }
    }

    @Override
    public void createChannelSuccess() {
        if (type.equals(TYPE_SET)) {
            ToastUtilsKt.toast(this, "修改成功");
        } else {
            ToastUtilsKt.toast(this, "创建成功");
        }
        EventBus.getDefault().post(KnowledgeChannelActivity.MESSAGE);
        EventBus.getDefault().post(EnterpriseKnowledgeActivity.MESSAGE);
        back();
    }
}
