package com.tuzhi.application.moudle.enterpriseknowledge.knowledgedetails.createdocument.mvp;


import android.app.Dialog;
import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Base64;

import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoImpl;
import com.jph.takephoto.model.InvokeParam;
import com.jph.takephoto.model.TContextWrap;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.permission.InvokeListener;
import com.jph.takephoto.permission.PermissionManager;
import com.jph.takephoto.permission.TakePhotoInvocationHandler;
import com.tencent.smtt.sdk.ValueCallback;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.tuzhi.application.R;
import com.tuzhi.application.databinding.ActivityCreateDocumentBinding;
import com.tuzhi.application.dialog.WarnDialog;
import com.tuzhi.application.inter.DialogMakeSureListener;
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
    private boolean isReady;
    private String editContentUrl;
    private WarnDialog dialog;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_create_document;
    }

    @Override
    protected void init(ViewDataBinding viewDataBinding) {
        binding = (ActivityCreateDocumentBinding) viewDataBinding;
        binding.setActivity(this);
        EmotionKeyboard.with(this).bindToContent(binding.contentView).bindToEmotionButton(binding.ivShowBar).bindToWebView(binding.wv).setEmotionView(binding.functionBar).build();
        id = getIntent().getStringExtra(ID);
        editContentUrl = getIntent().getStringExtra(CONTENT);
        WebSettings settings = binding.wv.getSettings();
        settings.setJavaScriptEnabled(true);
        binding.wv.loadUrl(editContentUrl);
        binding.wv.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                isReady = url.equalsIgnoreCase(editContentUrl);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String s) {
                webView.loadUrl(s);
                return true;
            }
        });

        dialog = new WarnDialog.Builder().setTitle("提示").setInfo("是否放弃保存,直接退出").setBtnLeftText("取消").setBtnRightText("确定").setClickListener(new DialogMakeSureListener() {
            @Override
            public void makeSure(Dialog dialog) {
                CreateDocumentActivity.super.onBackPressed();
            }
        }).builder(this);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getTakePhoto().onCreate(savedInstanceState);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        mPresenter.inform(id);
        super.onDestroy();
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
        mPresenter.uploadImage(binding.wv, new File(result.getImage().getOriginalPath()));
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
            dialog.show();
        }
    }

    @Override
    public void commit() {
        binding.wv.evaluateJavascript("javascript:document.getElementById('editor-trigger').innerHTML", new ValueCallback<String>() {
            @Override
            public void onReceiveValue(String value) {
                value = value.replace("\\u003C", "<").replace("&quot;", "\"").replace("\\","");
                value = value.substring(1, value.length() - 1);
                if (TextUtils.isEmpty(value) || TextUtils.equals(value, "<p style=\"line-height: 1.5;word-break: break-all;\"><br></p>")) {
                    mPresenter.commit(id, "");
                } else {
                    mPresenter.commit(id, Base64.encodeToString(value.getBytes(), Base64.DEFAULT));
                }
            }
        });
    }

    @Override
    public void commitSuccess() {
        setResult(ConstantKt.getNEED_REFRESH_CODE());
        finish();
    }

    @Override
    public void uploadImageSuccess(String imageUrl) {
        insertImage(imageUrl);
    }

    public void insertImage(String url) {
        exec("javascript:AddImage('" + url + "');");
    }

    public void undo() {
        exec("javascript:Undo();");
    }

    public void redo() {
        exec("javascript:Redo();");
    }

    public void setBold() {
        exec("javascript:Bold();");
    }

    public void setItalic() {
        exec("javascript:Italic();");
    }

    public void setHeading(int heading) {
        exec("javascript:ChangeSize('" + heading + "');");
    }

    public void setStrikeThrough() {
        exec("javascript:StrikeThrough();");
    }

    public void setUnderline() {
        exec("javascript:Underline();");
    }

    public void setIndent() {
        exec("javascript:Indent();");
    }

    public void setOutdent() {
        exec("javascript:Outdent();");
    }

    public void setAlignLeft() {
        exec("javascript:TextLeft();");
    }

    public void setAlignCenter() {
        exec("javascript:TextCenter();");
    }

    public void setAlignRight() {
        exec("javascript:TextRight();");
    }

    public void setBullets() {
        exec("javascript:List();");
    }

    public void setNumbers() {
        exec("javascript:Order();");
    }


    protected void exec(final String trigger) {
        if (isReady) {
            load(trigger);
        } else {
            binding.wv.postDelayed(new Runnable() {
                @Override
                public void run() {
                    exec(trigger);
                }
            }, 100);
        }
    }

    private void load(String trigger) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            binding.wv.evaluateJavascript(trigger, null);
        } else {
            binding.wv.loadUrl(trigger);
        }
    }
}
