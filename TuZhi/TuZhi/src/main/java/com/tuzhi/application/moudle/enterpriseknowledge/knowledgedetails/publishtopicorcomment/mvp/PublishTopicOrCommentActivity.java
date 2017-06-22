package com.tuzhi.application.moudle.enterpriseknowledge.knowledgedetails.publishtopicorcomment.mvp;


import android.databinding.ViewDataBinding;

import com.tuzhi.application.R;
import com.tuzhi.application.databinding.ActivityPublishTopicOrCommentBinding;
import com.tuzhi.application.moudle.basemvp.MVPBaseActivity;
import com.tuzhi.application.utils.ConstantKt;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class PublishTopicOrCommentActivity extends MVPBaseActivity<PublishTopicOrCommentContract.View, PublishTopicOrCommentPresenter> implements PublishTopicOrCommentContract.View {

    public static final String TYPE = "TYPE";
    public static final String TOPIC = "TOPIC";
    public static final String COMMENT = "COMMENT";
    public static final String AID = "AID";
    public static final String CID = "AID";
    private String type;
    private String aid;
    private String cid;

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
                binding.tvTitle.setText("发表话题");
                break;
            default:
                binding.tvTitle.setText("发表评论");
                break;
        }
    }

    public void commit(String text) {
        if (type.equals(TOPIC))
            mPresenter.commit(type, aid, text);
        else {
            mPresenter.commit(type, cid, text);
        }
    }

    public void back() {
        onBackPressed();
    }

    @Override
    public void commitSuccess() {
        setResult(ConstantKt.getNEED_REFRESH_CODE());
        back();
    }
}
