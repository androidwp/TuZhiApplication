package com.tuzhi.application.moudle.createchannel;


import android.databinding.ViewDataBinding;
import android.text.TextUtils;
import android.view.View;

import com.tuzhi.application.R;
import com.tuzhi.application.databinding.ActivityCreateChannelBinding;
import com.tuzhi.application.dialog.ChooseOpennessDialog;
import com.tuzhi.application.inter.OnDialogClickListener;
import com.tuzhi.application.moudle.basemvp.MVPBaseActivity;
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

    public static final String ID = "id";
    private ActivityCreateChannelBinding binding;
    private CreateChannelBean bean;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_create_channel;
    }

    @Override
    protected void init(ViewDataBinding viewDataBinding) {
        bean = new CreateChannelBean();
        binding = (ActivityCreateChannelBinding) viewDataBinding;
        binding.setActivity(this);
        binding.setData(bean);
        bean.setKlId(getIntent().getStringExtra(ID));
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
        ChooseOpennessDialog dialog = new ChooseOpennessDialog(this, bean.isOpenOrSecret(), 1);
        dialog.setClickListener(this);
        dialog.show();
    }

    @Override
    public void onDialogClick(View view) {
        switch (view.getId()) {
            case R.id.flOpen:
                bean.setOpenOrSecret(true);
                break;
            case R.id.flSecret:
                bean.setOpenOrSecret(false);
                break;
            default:
                break;
        }
    }

    @Override
    public void createChannelSuccess() {
        ToastUtilsKt.toast(this, "创建成功");
        EventBus.getDefault().post(KnowledgeChannelActivity.MESSAGE);
        back();
    }
}
