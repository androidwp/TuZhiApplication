package com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.publishtopicorcomment.mvp;


import android.app.Dialog;
import android.databinding.ViewDataBinding;
import android.text.TextUtils;

import com.tuzhi.application.R;
import com.tuzhi.application.databinding.ActivityPublishTopicOrCommentBinding;
import com.tuzhi.application.dialog.WarnDialog;
import com.tuzhi.application.inter.DialogMakeSureListener;
import com.tuzhi.application.moudle.basemvp.MVPBaseActivity;
import com.tuzhi.application.moudle.message.read.mvp.ReadFragment;
import com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.mvp.KnowledgeDetailsActivity;
import com.tuzhi.application.utils.ConstantKt;

import org.greenrobot.eventbus.EventBus;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class PublishTopicOrCommentActivity extends MVPBaseActivity<PublishTopicOrCommentContract.View, PublishTopicOrCommentPresenter> implements PublishTopicOrCommentContract.View {

    public static final String TYPE = "TYPE";
    public static final String TOPIC = "TOPIC";
    public static final String COMMENT = "COMMENT";
    public static final String AID = "AID";
    public static final String CID = "CID";
    private String type;
    private String aid;
    private String cid;
    private WarnDialog dialog;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_publish_topic_or_comment;
    }

    @Override
    protected void init(ViewDataBinding viewDataBinding) {
        ActivityPublishTopicOrCommentBinding binding = (ActivityPublishTopicOrCommentBinding) viewDataBinding;
        binding.setText("");
        binding.setActivity(this);
        aid = getIntent().getStringExtra(AID);
        cid = getIntent().getStringExtra(CID);
        type = getIntent().getStringExtra(TYPE);
        switch (type) {
            case TOPIC:
                binding.tvTitle.setText("发言");
                break;
            default:
                binding.tvTitle.setText("发表评论");
                break;
        }
        dialog = new WarnDialog.Builder().setTitle("提示").setInfo("是否放弃保存,直接退出").setBtnLeftText("取消").setBtnRightText("确定").setClickListener(new DialogMakeSureListener() {
            @Override
            public void makeSure(Dialog dialog) {
                PublishTopicOrCommentActivity.super.onBackPressed();
            }
        }).builder(this);
    }


    @Override
    public void onBackPressed() {
        dialog.show();
    }

    public void commit(String text) {
        if (type.equals(TOPIC))
            mPresenter.commit(type, aid, null, text);
        else {
            mPresenter.commit(type, aid, cid, text);
        }
    }

    public void back() {
        onBackPressed();
    }

    @Override
    public void commitSuccess() {
        if (TextUtils.equals(type,TOPIC)){
            EventBus.getDefault().post(KnowledgeDetailsActivity.MESSAGE);
        }else {
            EventBus.getDefault().post(ReadFragment.REFRESH);
            setResult(ConstantKt.getNEED_REFRESH_CODE());
        }
        super.onBackPressed();
    }
}
