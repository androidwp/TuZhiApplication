package com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.commentlist.mvp;


import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import com.tuzhi.application.R;
import com.tuzhi.application.databinding.ActivityCommentListBinding;
import com.tuzhi.application.inter.ItemClickListener;
import com.tuzhi.application.item.GeneralEmptyFootViewItem;
import com.tuzhi.application.item.GeneralLoadFootViewItem;
import com.tuzhi.application.moudle.basemvp.MVPBaseActivity;
import com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.commentlist.bean.CommentListBean;
import com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.commentlist.item.CommentListHeadItem;
import com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.commentlist.item.CommentListItem;
import com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.mvp.KnowledgeDetailsActivity;
import com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.publishtopicorcomment.mvp.PublishTopicOrCommentActivity;
import com.tuzhi.application.utils.ConstantKt;
import com.tuzhi.application.view.ActionSheet;
import com.tuzhi.application.view.LoadMoreListener;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import kale.adapter.CommonRcvAdapter;
import kale.adapter.item.AdapterItem;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class CommentListActivity extends MVPBaseActivity<CommentListContract.View, CommentListPresenter> implements CommentListContract.View, SwipeRefreshLayout.OnRefreshListener, LoadMoreListener, ActionSheet.ActionSheetListener, ItemClickListener {
    public static final String AID = "AID";
    public static final String CID = "CID";
    private String aid;
    private String cid;
    private ActionSheet actionSheet;
    private ActivityCommentListBinding binding;
    private ArrayList<CommentListBean> mData = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_comment_list;
    }

    @Override
    protected void init(ViewDataBinding viewDataBinding) {
        aid = getIntent().getStringExtra(AID);
        cid = getIntent().getStringExtra(CID);
        binding = (ActivityCommentListBinding) viewDataBinding;
        binding.setActivity(this);
        binding.rrv.isShowRefreshView(true);
        binding.rrv.setOnRefreshListener(this);
        binding.rrv.setLoadListener(this);
        binding.rrv.setTitle("还没有任何人评论哦");
        binding.rrv.setInfo("点击右上方按钮添加评论");
        CommonRcvAdapter<CommentListBean> adapter = new CommonRcvAdapter<CommentListBean>(mData) {
            @NonNull
            @Override
            public AdapterItem createItem(Object o) {
                String type = (String) o;
                switch (type) {
                    case GeneralLoadFootViewItem.TYPE:
                        return new GeneralLoadFootViewItem();
                    case GeneralEmptyFootViewItem.TYPE:
                        return new GeneralEmptyFootViewItem();
                    case CommentListItem.TYPE:
                        CommentListItem commentListItem = new CommentListItem();
                        commentListItem.setClickListener(CommentListActivity.this);
                        return commentListItem;
                    default:
                        return new CommentListHeadItem();
                }
            }

            @Override
            public Object getItemType(CommentListBean commentListBean) {
                return commentListBean.getItemType();
            }
        };
        binding.rrv.setAdapter(adapter);
    }

    public void back() {
        onBackPressed();
    }

    public void openSelectDialog() {
        actionSheet = ActionSheet.createBuilder(this, getSupportFragmentManager())
                .setCancelButtonTitle("取消")
                .setOtherButtonTitles("删除")
                .setCancelableOnTouchOutside(true)
                .setListener(this).show();
    }

    public void skipPublishCommentActivity() {
        Intent intent = new Intent(this, PublishTopicOrCommentActivity.class);
        intent.putExtra(PublishTopicOrCommentActivity.TYPE, PublishTopicOrCommentActivity.COMMENT);
        intent.putExtra(PublishTopicOrCommentActivity.AID, aid);
        intent.putExtra(PublishTopicOrCommentActivity.CID, cid);
        startActivityForResult(intent, ConstantKt.getNEED_REFRESH_CODE());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ConstantKt.getNEED_REFRESH_CODE() && resultCode == ConstantKt.getNEED_REFRESH_CODE()) {
            EventBus.getDefault().post(KnowledgeDetailsActivity.MESSAGE);
            onRefresh();
        }
    }

    @Override
    public void onRefresh() {
        mPresenter.downLoadData(aid, cid, 0);
    }

    @Override
    public void loadMoreListener(int page) {
        mPresenter.downLoadData(aid, cid, page);
    }

    @Override
    public void onBackPressed() {
        if (actionSheet != null && !actionSheet.ismDismissed()) {
            actionSheet.dismiss();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onDismiss(ActionSheet actionSheet, boolean isCancel) {
        actionSheet.dismiss();
    }

    @Override
    public void onOtherButtonClick(ActionSheet actionSheet, int index) {
        mPresenter.deleteCommentList(cid);
    }


    @Override
    public void downLoadFinish(int page, boolean haveNextPage, ArrayList<CommentListBean> data, boolean praiseStatus, String praiseNumber) {
        binding.rrv.downLoadFinish(page, haveNextPage, mData, data, true);
        binding.setPraiseStatus(praiseStatus);
        binding.setPraiseNumber(praiseNumber);
    }

    @Override
    public void clickPraiseSuccess(String praiseNumber) {
        binding.setPraiseStatus(true);
        binding.setPraiseNumber(praiseNumber);
        EventBus.getDefault().post(KnowledgeDetailsActivity.MESSAGE);
    }

    @Override
    public void deleteCommentListSuccess() {
        EventBus.getDefault().post(KnowledgeDetailsActivity.MESSAGE);
        onBackPressed();
    }

    @Override
    public void deleteCommentSuccess() {
        onRefresh();
    }

    public void commitPraise(boolean praiseStatus, String praiseNumber) {
        if (!praiseStatus)
            mPresenter.commitClickPraise(aid, cid, praiseNumber);
    }

    @Override
    public void onItemClick(View view) {
        String id = (String) view.getTag();
        mPresenter.deleteComment(id);
    }
}
