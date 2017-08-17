package com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.mvp;


import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.tuzhi.application.R;
import com.tuzhi.application.databinding.ActivityKnowledgeDetailsBinding;
import com.tuzhi.application.dialog.DeleteDialog;
import com.tuzhi.application.dialog.ProgressBarDialog;
import com.tuzhi.application.dialog.RenameDialog;
import com.tuzhi.application.inter.OnDialogClickListener;
import com.tuzhi.application.item.GeneralEmptyFootViewItem;
import com.tuzhi.application.item.GeneralLoadFootViewItem;
import com.tuzhi.application.moudle.basemvp.MVPBaseActivity;
import com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.bean.KnowledgeDetailsListBean;
import com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.checkhistoricalversion.mvp.CheckHistoricalVersionActivity;
import com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.createdocument.mvp.CreateDocumentActivity;
import com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.item.KnowledgeDetailsArticleItem;
import com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.item.KnowledgeDetailsCommentItem;
import com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.item.KnowledgeDetailsFilesItem;
import com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.publishtopicorcomment.mvp.PublishTopicOrCommentActivity;
import com.tuzhi.application.moudle.repository.enterpriseknowledge.mvp.EnterpriseKnowledgeActivity;
import com.tuzhi.application.utils.ActivitySkipUtilsKt;
import com.tuzhi.application.utils.ConstantKt;
import com.tuzhi.application.utils.KeyBoardUtils;
import com.tuzhi.application.utils.ToastUtilsKt;
import com.tuzhi.application.view.ActionSheet;
import com.tuzhi.application.view.LoadMoreListener;
import com.yanzhenjie.album.Album;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import kale.adapter.CommonRcvAdapter;
import kale.adapter.item.AdapterItem;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class KnowledgeDetailsActivity extends MVPBaseActivity<KnowledgeDetailsContract.View, KnowledgeDetailsPresenter> implements KnowledgeDetailsContract.View, LoadMoreListener, SwipeRefreshLayout.OnRefreshListener, ActionSheet.ActionSheetListener, OnDialogClickListener {
    public static final String MESSAGE = "EKnowledgeDetailsActivity_refresh";
    public static final String ID = "ID";
    public static final String TITLE = "TITLE";
    private ArrayList<KnowledgeDetailsListBean> data = new ArrayList<>();
    private String id;
    private ActivityKnowledgeDetailsBinding binding;
    private ActionSheet actionSheet;
    private int type;
    private ProgressBarDialog dialog;
    private String title;
    private boolean flagCanClick = true;
    private RenameDialog renameDialog;
    private DeleteDialog deleteDialog;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_knowledge_details;
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(String event) {
        if (TextUtils.equals(event, MESSAGE))
            onRefresh();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    //type=0  为删除  重命名，type=1  为选择图库
    public void openSelectDialog(int type) {
        this.type = type;
        String textOne;
        String textTwo;
        if (type == 0) {
            textOne = "重命名频道";
            textTwo = "删除频道";
            actionSheet = ActionSheet.createBuilder(this, getSupportFragmentManager())
                    .setCancelButtonTitle("取消")
                    .setOtherButtonTitles("查看笔记历史版本", textOne, textTwo)
                    .setCancelableOnTouchOutside(true)
                    .setListener(this).show();

        } else {
            textOne = "从相册选择";
            textTwo = "拍摄新的照片";
            actionSheet = ActionSheet.createBuilder(this, getSupportFragmentManager())
                    .setCancelButtonTitle("取消")
                    .setOtherButtonTitles(textOne, textTwo)
                    .setCancelableOnTouchOutside(true)
                    .setListener(this).show();
        }

    }

    @Override
    protected void init(ViewDataBinding viewDataBinding) {
        binding = (ActivityKnowledgeDetailsBinding) viewDataBinding;
        id = getIntent().getStringExtra(ID);
        title = getIntent().getStringExtra(TITLE);
        setTitle(title);
        binding.setActivity(this);
        binding.rrv.isShowRefreshView(true);
        binding.rrv.setOnRefreshListener(this);
        binding.rrv.setLoadListener(this);
        CommonRcvAdapter<KnowledgeDetailsListBean> adapter = new CommonRcvAdapter<KnowledgeDetailsListBean>(data) {
            @NonNull
            @Override
            public AdapterItem createItem(Object o) {
                String itemType = (String) o;
                switch (itemType) {
                    case KnowledgeDetailsArticleItem.TYPE:
                        return new KnowledgeDetailsArticleItem();
                    case KnowledgeDetailsFilesItem.TYPE:
                        return new KnowledgeDetailsFilesItem();
                    case KnowledgeDetailsCommentItem.TYPE:
                        return new KnowledgeDetailsCommentItem();
                    case GeneralEmptyFootViewItem.TYPE:
                        return new GeneralEmptyFootViewItem();
                    default:
                        return new GeneralLoadFootViewItem();
                }
            }

            @Override
            public Object getItemType(KnowledgeDetailsListBean knowledgeDetailsListBean) {
                return knowledgeDetailsListBean.getItemType();
            }
        };

        binding.rrv.setAdapter(adapter);
    }

    public void back() {
        onBackPressed();
    }

    @Override
    public void loadMoreListener(int page) {
        mPresenter.downLoadData(id, page);
    }

    @Override
    public void onRefresh() {
        KnowledgeDetailsArticleItem.isCreateWebview = true;
        mPresenter.downLoadData(id, 0);
    }

    @Override
    public void downLoadFinish(ArrayList<KnowledgeDetailsListBean> newData, boolean haveNextPage, int page) {
        binding.rrv.downLoadFinish(page, haveNextPage, data, newData, true);
    }

    @Override
    public void downloadFinish() {
        binding.rrv.isShowRefreshView(false);
    }

    @Override
    public void updateProgress(int finishNumber, int totalNumber) {
        dialog.changeProgress(finishNumber, totalNumber);
    }

    @Override
    public void updateFinish() {
        dialog.dismiss();
        EventBus.getDefault().post(EnterpriseKnowledgeActivity.MESSAGE);
        onRefresh();
    }

    public void skipPublishTopicActivity() {
        Intent intent = new Intent(this, PublishTopicOrCommentActivity.class);
        intent.putExtra(PublishTopicOrCommentActivity.TYPE, PublishTopicOrCommentActivity.TOPIC);
        intent.putExtra(PublishTopicOrCommentActivity.AID, id);
        startActivity(intent);
    }


    public void skipCreateDocumentActivity() {
        if (flagCanClick) {
            flagCanClick = false;
            mPresenter.skipCreateDocumentActivity(id);
        }
    }


    public void skipCreateDocumentActivity(String editContentUrl) {
        if (!TextUtils.isEmpty(editContentUrl)) {
            canClick();
            Intent intent = new Intent(this, CreateDocumentActivity.class);
            intent.putExtra(CreateDocumentActivity.ID, id);
            intent.putExtra(CreateDocumentActivity.CONTENT, editContentUrl);
            startActivityForResult(intent, ConstantKt.getNEED_REFRESH_CODE());
        }
    }

    @Override
    public void canClick() {
        flagCanClick = true;
    }

    @Override
    public void deleteSuccess() {
        ToastUtilsKt.toast(this, "删除成功");
        EventBus.getDefault().post(EnterpriseKnowledgeActivity.MESSAGE);
        deleteDialog.dismiss();
        super.onBackPressed();
    }

    @Override
    public void renameSuccess(String name) {
        ToastUtilsKt.toast(this, "修改成功");
        EventBus.getDefault().post(EnterpriseKnowledgeActivity.MESSAGE);
        setTitle(name);
        renameDialog.dismiss();
    }

    @Override
    public void onDismiss(ActionSheet actionSheet, boolean isCancel) {
        actionSheet.dismiss();
    }

    @Override
    public void onOtherButtonClick(ActionSheet actionSheet, int index) {
        if (type == 0) {
            if (index == 0) {
                //跳转到查看历史版本
                ActivitySkipUtilsKt.toActivity(this, CheckHistoricalVersionActivity.class, CheckHistoricalVersionActivity.ID, id);
            } else if (index == 1) {
                renameDialog = new RenameDialog(this, R.style.dialog);
                renameDialog.setView(new EditText(this));
                renameDialog.setClickListener(this);
                renameDialog.setText(title);
                renameDialog.show();
                KeyBoardUtils.showKeyBoard(this);
            } else {
                deleteDialog = new DeleteDialog(this, R.style.dialog);
                deleteDialog.setClickListener(this);
                deleteDialog.show();
            }
        } else {
            if (index == 0) {
                //多选图片
                Album.album(this)
                        .title("图库") // 配置title。
                        .selectCount(9) // 最多选择几张图片。
                        .columnCount(3) // 相册展示列数，默认是2列。
                        .camera(false) // 是否有拍照功能。
                        .start(999); // 999是请求码，返回时onActivityResult()的第一个参数。
            } else {
                //打开相机
                Album.camera(this).start(999);
            }
        }

        actionSheet.dismiss();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 999) {
            if (resultCode == RESULT_OK) { // Successfully.
                ArrayList<String> pathList = Album.parseResult(data); // Parse path.
                mPresenter.uploadFiles(binding.rrv, id, pathList);
                dialog = new ProgressBarDialog(this);
                dialog.setTitle("正在上传");
                dialog.setClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPresenter.cancelUpdate();
                    }
                });
                dialog.show();
            }
        }
    }


    @Override
    public void onBackPressed() {
        if (actionSheet != null && !actionSheet.ismDismissed()) {
            actionSheet.dismiss();
        } else {
            super.onBackPressed();
        }
    }

    public void setTitle(String title) {
        this.title = title;
        binding.tvTitle.setText(title);
    }

    @Override
    public void onDialogClick(View view) {
        switch (view.getId()) {
            case R.id.tvDelete:
                mPresenter.deleteCard(id);
                break;
            case R.id.tvRename:
                mPresenter.renameCard(id, (String) view.getTag());
                break;
        }
    }
}
