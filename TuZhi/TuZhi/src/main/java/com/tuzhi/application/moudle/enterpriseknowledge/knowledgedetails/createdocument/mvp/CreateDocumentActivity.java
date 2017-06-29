package com.tuzhi.application.moudle.enterpriseknowledge.knowledgedetails.createdocument.mvp;


import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoImpl;
import com.jph.takephoto.model.InvokeParam;
import com.jph.takephoto.model.TContextWrap;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.permission.InvokeListener;
import com.jph.takephoto.permission.PermissionManager;
import com.jph.takephoto.permission.TakePhotoInvocationHandler;
import com.tuzhi.application.R;
import com.tuzhi.application.databinding.ActivityCreateDocumentBinding;
import com.tuzhi.application.moudle.basemvp.MVPBaseActivity;
import com.tuzhi.application.utils.ConstantKt;
import com.tuzhi.application.utils.EmotionKeyboard;
import com.tuzhi.application.view.ActionSheet;

import java.io.File;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class CreateDocumentActivity extends MVPBaseActivity<CreateDocumentContract.View, CreateDocumentPresenter> implements CreateDocumentContract.View, ActionSheet.ActionSheetListener, TakePhoto.TakeResultListener, InvokeListener {

    public static final String CONTENT = "CONTENT";
    public static final String ID = "id";
    private static final String PORTRAIT_NAME = "portrait.png";
    private TakePhoto takePhoto;
    private InvokeParam invokeParam;
    private ActionSheet actionSheet;
    private String id;
    private ActivityCreateDocumentBinding binding;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_create_document;
    }

    @Override
    protected void init(ViewDataBinding viewDataBinding) {
        binding = (ActivityCreateDocumentBinding) viewDataBinding;
        binding.setActivity(this);
        binding.setRichEditor(binding.re);
        EmotionKeyboard.with(this).bindToContent(binding.contentView).bindToEmotionButton(binding.ivShowBar).bindToWebView(binding.re).setEmotionView(binding.functionBar).build();
        String content = getIntent().getStringExtra(CONTENT);
        id = getIntent().getStringExtra(ID);
        if (!TextUtils.isEmpty(content)) {
            binding.re.setHtml(content);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getTakePhoto().onCreate(savedInstanceState);
    }

    public TakePhoto getTakePhoto() {
        if (takePhoto == null) {
            takePhoto = (TakePhoto) TakePhotoInvocationHandler.of(this).bind(new TakePhotoImpl(this, this));
        }
        return takePhoto;
    }

    public void back() {
        onBackPressed();
    }

    @Override
    public void takeSuccess(TResult result) {
        mPresenter.uploadImage(binding.re, new File(result.getImage().getOriginalPath()));
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        getTakePhoto().onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void openSelectDialog() {
        actionSheet = ActionSheet.createBuilder(this, getSupportFragmentManager())
                .setCancelButtonTitle("取消")
                .setOtherButtonTitles("从相册选择", "拍摄新的照片")
                .setCancelableOnTouchOutside(true)
                .setListener(this).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.TPermissionType type = PermissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.handlePermissionsResult(this, type, invokeParam, this);
    }

    @Override
    public void onOtherButtonClick(ActionSheet actionSheet, int index) {
        if (index == 0) {
            takePhoto.onPickFromGallery();
        } else {
            takePhoto.onPickFromCapture(Uri.fromFile(ConstantKt.getImageCache(this, PORTRAIT_NAME)));
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

    @Override
    public void commit(String content) {
        if (TextUtils.isEmpty(content)||TextUtils.equals(content,"\n\t<p style=\"line-height: 1.5;word-break: break-all;\"><br></p>")) {
            mPresenter.commit(id, "");
        } else {
            mPresenter.commit(id, content);
        }
    }

    @Override
    public void commitSuccess() {
        setResult(ConstantKt.getNEED_REFRESH_CODE());
        back();
    }

    @Override
    public void uploadImageSuccess(String imageUrl) {
        binding.re.insertImage(imageUrl + "\" style=\"width:100%;", "");
    }
}
