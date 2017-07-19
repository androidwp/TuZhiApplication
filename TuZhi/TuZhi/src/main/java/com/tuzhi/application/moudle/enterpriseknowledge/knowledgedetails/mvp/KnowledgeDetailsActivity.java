package com.tuzhi.application.moudle.enterpriseknowledge.knowledgedetails.mvp;


import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoImpl;
import com.jph.takephoto.model.InvokeParam;
import com.jph.takephoto.model.TContextWrap;
import com.jph.takephoto.model.TImage;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.permission.InvokeListener;
import com.jph.takephoto.permission.PermissionManager;
import com.jph.takephoto.permission.TakePhotoInvocationHandler;
import com.tuzhi.application.R;
import com.tuzhi.application.databinding.ActivityKnowledgeDetailsBinding;
import com.tuzhi.application.dialog.DeleteDialog;
import com.tuzhi.application.dialog.ProgressBarDialog;
import com.tuzhi.application.dialog.RenameDialog;
import com.tuzhi.application.item.GeneralEmptyFootViewItem;
import com.tuzhi.application.item.GeneralLoadFootViewItem;
import com.tuzhi.application.moudle.basemvp.MVPBaseActivity;
import com.tuzhi.application.moudle.enterpriseknowledge.knowledgedetails.bean.KnowledgeDetailsListBean;
import com.tuzhi.application.moudle.enterpriseknowledge.knowledgedetails.createdocument.mvp.CreateDocumentActivity;
import com.tuzhi.application.moudle.enterpriseknowledge.knowledgedetails.item.KnowledgeDetailsArticleItem;
import com.tuzhi.application.moudle.enterpriseknowledge.knowledgedetails.item.KnowledgeDetailsCommentItem;
import com.tuzhi.application.moudle.enterpriseknowledge.knowledgedetails.item.KnowledgeDetailsFilesItem;
import com.tuzhi.application.moudle.enterpriseknowledge.knowledgedetails.publishtopicorcomment.mvp.PublishTopicOrCommentActivity;
import com.tuzhi.application.utils.ConstantKt;
import com.tuzhi.application.utils.KeyBoardUtils;
import com.tuzhi.application.utils.SharedPreferencesUtilsKt;
import com.tuzhi.application.view.ActionSheet;
import com.tuzhi.application.view.LoadMoreListener;

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

public class KnowledgeDetailsActivity extends MVPBaseActivity<KnowledgeDetailsContract.View, KnowledgeDetailsPresenter> implements KnowledgeDetailsContract.View, LoadMoreListener, SwipeRefreshLayout.OnRefreshListener, ActionSheet.ActionSheetListener, TakePhoto.TakeResultListener, InvokeListener {

    public static final String MESSAGE = "EKnowledgeDetailsActivity_refresh";
    private static final String PHOTO = "photo.png";
    public static final String ID = "ID";
    public static final String TITLE = "TITLE";

    private ArrayList<KnowledgeDetailsListBean> data = new ArrayList<>();
    private String id;
    private ActivityKnowledgeDetailsBinding binding;
    private TakePhoto takePhoto;
    private InvokeParam invokeParam;
    private ActionSheet actionSheet;
    private int type;
    private ProgressBarDialog dialog;
    private String title;
    private String flagDeleteMoudle;

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
        getTakePhoto().onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public TakePhoto getTakePhoto() {
        if (takePhoto == null) {
            takePhoto = (TakePhoto) TakePhotoInvocationHandler.of(this).bind(new TakePhotoImpl(this, this));
        }
        return takePhoto;
    }


    //type=0  为删除  重命名，type=1  为选择图库
    public void openSelectDialog(int type) {
        this.type = type;
        String textOne;
        String textTwo;
        if (type == 0) {
            textOne = "重命名频道";
            textTwo = "删除频道";
            if (TextUtils.equals(flagDeleteMoudle, ConstantKt.getValue_true())) {
                actionSheet = ActionSheet.createBuilder(this, getSupportFragmentManager())
                        .setCancelButtonTitle("取消")
                        .setOtherButtonTitles(textOne, textTwo)
                        .setCancelableOnTouchOutside(true)
                        .setListener(this).show();
            } else {
                actionSheet = ActionSheet.createBuilder(this, getSupportFragmentManager())
                        .setCancelButtonTitle("取消")
                        .setOtherButtonTitles(textOne)
                        .setCancelableOnTouchOutside(true)
                        .setListener(this).show();
            }
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
        flagDeleteMoudle = SharedPreferencesUtilsKt.getLongCache(this, ConstantKt.getFLAG_DELETE_MOUDLE());
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
        onRefresh();
    }

    public void skipPublishTopicActivity() {
        Intent intent = new Intent(this, PublishTopicOrCommentActivity.class);
        intent.putExtra(PublishTopicOrCommentActivity.TYPE, PublishTopicOrCommentActivity.TOPIC);
        intent.putExtra(PublishTopicOrCommentActivity.AID, id);
        startActivityForResult(intent, ConstantKt.getNEED_REFRESH_CODE());
    }


    public void skipCreateDocumentActivity() {
        mPresenter.skipCreateDocumentActivity(id);
    }


    public void skipCreateDocumentActivity(String editContentUrl) {
        if (!TextUtils.isEmpty(editContentUrl)) {
            Intent intent = new Intent(this, CreateDocumentActivity.class);
            intent.putExtra(CreateDocumentActivity.ID, id);
            intent.putExtra(CreateDocumentActivity.CONTENT, editContentUrl);
            startActivityForResult(intent, ConstantKt.getNEED_REFRESH_CODE());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        getTakePhoto().onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ConstantKt.getNEED_REFRESH_CODE() && resultCode == ConstantKt.getNEED_REFRESH_CODE()) {
            onRefresh();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.TPermissionType type = PermissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.handlePermissionsResult(this, type, invokeParam, this);
    }

    @Override
    public void takeSuccess(TResult result) {
        ArrayList<TImage> images = result.getImages();
        if (images == null || images.size() == 0) {
            images = new ArrayList<>();
            images.add(result.getImage());
        }
        mPresenter.uploadFiles(binding.rrv, id, images);
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

    @Override
    public void takeFail(TResult result, String msg) {

    }

    @Override
    public void takeCancel() {

    }

    @Override
    public PermissionManager.TPermissionType invoke(InvokeParam invokeParam) {
        PermissionManager.TPermissionType type = PermissionManager.checkPermission(TContextWrap.of(this), invokeParam.getMethod());
        if (PermissionManager.TPermissionType.WAIT.equals(type)) {
            this.invokeParam = invokeParam;
        }
        return type;
    }

    @Override
    public void onDismiss(ActionSheet actionSheet, boolean isCancel) {
        actionSheet.dismiss();
    }

    @Override
    public void onOtherButtonClick(ActionSheet actionSheet, int index) {
        if (type == 0) {
            if (index == 0) {
                RenameDialog renameDialog = new RenameDialog(this, R.style.dialog);
                renameDialog.setView(new EditText(this));
                renameDialog.setMoudleId(id);
                renameDialog.setType(RenameDialog.MOUDLE);
                renameDialog.setText(title);
                renameDialog.show();
                KeyBoardUtils.showKeyBoard(this);
            } else {
                DeleteDialog deleteDialog = new DeleteDialog(this, R.style.dialog);
                deleteDialog.setMoudleId(id);
                deleteDialog.setType(RenameDialog.MOUDLE);
                deleteDialog.show();
            }
        } else {
            if (index == 0) {
                takePhoto.onPickMultiple(9);
            } else {
                takePhoto.onPickFromCapture(Uri.fromFile(ConstantKt.getImageCache(this, PHOTO)));
            }
        }

        actionSheet.dismiss();
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

}
