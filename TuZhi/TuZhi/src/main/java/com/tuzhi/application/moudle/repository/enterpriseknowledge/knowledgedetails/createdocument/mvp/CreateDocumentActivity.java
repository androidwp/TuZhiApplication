package com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.createdocument.mvp;


import android.app.Dialog;
import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.os.Build;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;

import com.tencent.smtt.export.external.interfaces.JsResult;
import com.tencent.smtt.sdk.ValueCallback;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.tuzhi.application.R;
import com.tuzhi.application.databinding.ActivityCreateDocumentBinding;
import com.tuzhi.application.dialog.WarnDialog;
import com.tuzhi.application.inter.DialogMakeSureListener;
import com.tuzhi.application.moudle.basemvp.MVPBaseActivity;
import com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.mvp.KnowledgeDetailsActivity;
import com.tuzhi.application.utils.EmotionKeyboard;
import com.tuzhi.application.view.ActionSheet;
import com.yanzhenjie.album.Album;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.List;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class CreateDocumentActivity extends MVPBaseActivity<CreateDocumentContract.View, CreateDocumentPresenter> implements CreateDocumentContract.View, ActionSheet.ActionSheetListener {

    public static final String CONTENT = "CONTENT";
    public static final String ID = "id";
    private static final String PORTRAIT_NAME = "portrait.png";
    private ActionSheet actionSheet;
    private String id;
    private ActivityCreateDocumentBinding binding;
    private boolean isReady;
    private String editContentUrl;
    private WarnDialog dialog;
    private String initiativeToCancel = "0";

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
                exec("javascript:editHeight('600px');");
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String url) {
                if (!url.contains("/mobile/edit/timeout.html")) {
                    webView.loadUrl(url);
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            new WarnDialog.Builder().setCanceledOnTouchOutside(false).setTitle("提示").setInfo("当前编辑页面已超时，系统已自动退出编辑状态并将已修改内容保存为草稿。为防止草稿丢失，请及时返回继续编辑并提交").setShowCancel(false).setBtnRightText("确定").setClickListener(new DialogMakeSureListener() {
                                @Override
                                public void makeSure(Dialog dialog) {
                                    finish();
                                }
                            }).builder(CreateDocumentActivity.this).show();
                        }
                    });
                }
                return true;
            }
        });

        binding.wv.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView webView, String url, String message, final JsResult jsResult) {
                new WarnDialog.Builder().setTitle("提示").setInfo("当前编辑页面已超时，系统已自动退出编辑状态并将已修改内容保存为草稿。为防止草稿丢失，请及时返回继续编辑并提交").setShowCancel(false).setBtnRightText("确定").setClickListener(new DialogMakeSureListener() {

                    @Override
                    public void makeSure(Dialog dialog) {
                        jsResult.confirm();
                        finish();
                    }
                }).builder(CreateDocumentActivity.this).show();

                return true;
            }
        });

        dialog = new WarnDialog.Builder().setTitle("提示").setInfo("是否放弃保存,直接退出").setBtnLeftText("取消").setBtnRightText("确定").setClickListener(new DialogMakeSureListener() {
            @Override
            public void makeSure(Dialog dialog) {
                initiativeToCancel = "1";
                CreateDocumentActivity.super.onBackPressed();
            }
        }).builder(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        mPresenter.inform(id, initiativeToCancel);
        super.onDestroy();
    }


    public void back() {
        onBackPressed();
    }


    @Override
    public void onDismiss(ActionSheet actionSheet, boolean isCancel) {
        actionSheet.dismiss();
    }


    public void openSelectDialog() {
        actionSheet = ActionSheet.createBuilder(this, getSupportFragmentManager())
                .setCancelButtonTitle("取消")
                .setOtherButtonTitles("从相册选择", "拍摄新的照片")
                .setCancelableOnTouchOutside(true)
                .setListener(this).show();
    }


    @Override
    public void onOtherButtonClick(ActionSheet actionSheet, int index) {
        if (index == 0) {
            //打开单图
            Album.albumRadio(this)
                    .title("图库") // 配置title。
                    .columnCount(3) // 相册展示列数，默认是2列。
                    .camera(false) // 是否有拍照功能。
                    .start(999); // 999是请求码，返回时onActivityResult()的第一个参数。
        } else {
            //打开相机
            Album.camera(this).start(999);
        }
        actionSheet.dismiss();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 999) {
            if (resultCode == RESULT_OK) { // Successfully.
                // 这里的List的size肯定是1。
                List<String> pathList = Album.parseResult(data); // Parse path.
                File file = new File(pathList.get(0));
                mPresenter.uploadImage(binding.wv, file);
            }
        }
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
        binding.wv.evaluateJavascript("javascript:editTime();", new ValueCallback<String>() {
            @Override
            public void onReceiveValue(String s) {
                if (!TextUtils.isEmpty(s) && s.contains("0")) {
                    binding.wv.evaluateJavascript("javascript:document.getElementById('editor-trigger').innerHTML", new ValueCallback<String>() {
                        @Override
                        public void onReceiveValue(String value) {
                            value = value.replace("\\u003C", "<").replace("&quot;", "\"").replace("\\", "");
                            value = value.substring(1, value.length() - 1);
                            if (TextUtils.isEmpty(value) || TextUtils.equals(value, "<p><br></p>")) {
                                mPresenter.commit(id, "");
                            } else {
                                mPresenter.commit(id, Base64.encodeToString(value.getBytes(), Base64.DEFAULT));
                            }
                        }
                    });
                } else {
                    showWarnDialog();
                }
            }
        });
    }


    private void showWarnDialog() {
        new WarnDialog.Builder().setTitle("提示").setInfo("当前编辑页面已超时，系统已自动退出编辑状态并将已修改内容保存为草稿。为防止草稿丢失，请及时返回继续编辑并提交").setShowCancel(false).setBtnRightText("确定").setClickListener(new DialogMakeSureListener() {
            @Override
            public void makeSure(Dialog dialog) {
                finish();
            }
        }).builder(CreateDocumentActivity.this).show();
    }

    @Override
    public void commitSuccess() {
        EventBus.getDefault().post(KnowledgeDetailsActivity.MESSAGE);
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

    public void setBold(View view, boolean isChecked) {
        view.setBackgroundResource(isChecked ? R.drawable.general_editext_background : R.color.colorWhite);
        exec("javascript:Bold();");
    }

    public void setItalic(View view, boolean isChecked) {
        view.setBackgroundResource(isChecked ? R.drawable.general_editext_background : R.color.colorWhite);
        exec("javascript:Italic();");
    }

    public void setHeading(int heading) {
        exec("javascript:ChangeSize('" + heading + "');");
//        switch (heading) {
//            case 6:
//                exec("javascript:titleH2();");
//                break;
//            case 5:
//                exec("javascript:titleH3();");
//                break;
//            case 4:
//                exec("javascript:titleH4();");
//                break;
//            case 2:
//                exec("javascript:titlep();");
//                break;
//        }
    }

    public void setStrikeThrough(View view, boolean isChecked) {
        view.setBackgroundResource(isChecked ? R.drawable.general_editext_background : R.color.colorWhite);
        exec("javascript:StrikeThrough();");
    }

    public void setUnderline(View view, boolean isChecked) {
        view.setBackgroundResource(isChecked ? R.drawable.general_editext_background : R.color.colorWhite);
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

    public void setBullets(View view, boolean isChecked) {
        if (isChecked)
            binding.cbOrder.setChecked(false);
        view.setBackgroundResource(isChecked ? R.drawable.general_editext_background : R.color.colorWhite);
        exec("javascript:List();");
    }

    public void setNumbers(View view, boolean isChecked) {
        if (isChecked)
            binding.cbUnorder.setChecked(false);
        view.setBackgroundResource(isChecked ? R.drawable.general_editext_background : R.color.colorWhite);
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
