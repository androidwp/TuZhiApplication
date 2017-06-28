package com.tuzhi.application.moudle.enterpriseknowledge.knowledgedetails.openfile.mvp;


import android.app.Dialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;

import com.tuzhi.application.R;
import com.tuzhi.application.databinding.ActivityOpenFileBinding;
import com.tuzhi.application.dialog.DeleteDialog;
import com.tuzhi.application.dialog.RenameDialog;
import com.tuzhi.application.dialog.WarnDialog;
import com.tuzhi.application.inter.DialogMakeSureListener;
import com.tuzhi.application.moudle.basemvp.MVPBaseActivity;
import com.tuzhi.application.moudle.enterpriseknowledge.knowledgedetails.openfile.fragment.NotOpenFileFragment;
import com.tuzhi.application.moudle.enterpriseknowledge.knowledgedetails.openfile.fragment.OpenFileFragment;
import com.tuzhi.application.utils.ConstantKt;
import com.tuzhi.application.utils.FileUtils;
import com.tuzhi.application.utils.KeyBoardUtils;
import com.tuzhi.application.utils.NetworkUtils;
import com.tuzhi.application.utils.SharedPreferencesUtilsKt;
import com.tuzhi.application.view.ActionSheet;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.ArrayList;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class OpenFileActivity extends MVPBaseActivity<OpenFileContract.View, OpenFilePresenter> implements OpenFileContract.View, ActionSheet.ActionSheetListener {
    //标题--也是文件名字
    public static final String FILE_NAME = "FILE_NAME";
    //告知文件可否展示
    public static final String TYPE = "TYPE";
    //文章ID
    public static final String ARTICLE_ID = "ARTICLE_ID";
    //文件id
    public static final String FILE_ID = "FILE_ID";
    //文件下载地址
    public static final String FILE_URL = "FILE_URL";
    //文件大小
    public static final String FILE_SIZE = "FILE_SIZE";
    //文件浏览数据
    public static final String FILE_PREVIEW_URLS = "FILE_PREVIEW_URLS";
    //文件后缀类型
    public static final String FILE_SUFFIX = "fileSuffix";

    private ActionSheet actionSheet;
    private boolean type;
    private ActivityOpenFileBinding binding;
    private String fileId;
    private String fileUrl;
    private String fileName;
    private String fileSize;
    private NotOpenFileFragment notOpenFileFragment;
    private String articleId;
    private int downloadType;
    private String fileSffix;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_open_file;
    }

    @Override
    protected void init(ViewDataBinding viewDataBinding) {
        EventBus.getDefault().register(this);
        type = getIntent().getBooleanExtra(TYPE, false);
        fileId = getIntent().getStringExtra(FILE_ID);
        fileUrl = getIntent().getStringExtra(FILE_URL);
        fileName = getIntent().getStringExtra(FILE_NAME);
        fileSize = getIntent().getStringExtra(FILE_SIZE);
        articleId = getIntent().getStringExtra(ARTICLE_ID);
        fileSffix = getIntent().getStringExtra(FILE_SUFFIX);
        binding = (ActivityOpenFileBinding) viewDataBinding;
        binding.setActivity(this);
        binding.setTitle(fileName);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (type) {
            ArrayList<String> filePreviewUrls = getIntent().getStringArrayListExtra(FILE_PREVIEW_URLS);
            OpenFileFragment openFileFragment = new OpenFileFragment();
            Bundle bundle = new Bundle();
            bundle.putStringArrayList(FILE_PREVIEW_URLS, filePreviewUrls);
            openFileFragment.setArguments(bundle);
            fragmentTransaction.add(R.id.fl, openFileFragment).commit();
        } else {
            notOpenFileFragment = new NotOpenFileFragment();
            Bundle bundle = new Bundle();
            bundle.putString(FILE_ID, fileId);
            bundle.putString(FILE_URL, fileUrl);
            bundle.putString(FILE_NAME, fileName);
            bundle.putString(FILE_SIZE, fileSize);
            bundle.putString(FILE_SUFFIX, fileSffix);
            notOpenFileFragment.setArguments(bundle);
            fragmentTransaction.add(R.id.fl, notOpenFileFragment).commit();
        }
        mPresenter.reviewFile(articleId, fileId);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void back() {
        onBackPressed();
    }

    //type=0  为删除  重命名，type=1  为选择图库
    public void openSelectDialog() {
        ActionSheet.Builder builder = ActionSheet.createBuilder(this, getSupportFragmentManager()).setCancelButtonTitle("取消").setCancelableOnTouchOutside(true);
        if (type) {
            if (FileUtils.fileExist(this, fileId)) {
                builder.setOtherButtonTitles("用第三方应用打开", "重命名", "删除");
            } else {
                builder.setOtherButtonTitles("下载并用第三方应用打开", "重命名", "删除");
            }
        } else {
            builder.setOtherButtonTitles("重命名", "删除");
        }
        actionSheet = builder.setListener(this).show();
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
        if (type) {
            switch (index) {
                case 0:
                    downloadFile(1);
                    break;
                case 1:
                    rename();
                    break;
                case 2:
                    delete();
                    break;
            }
        } else {
            switch (index) {
                case 0:
                    rename();
                    break;
                case 1:
                    delete();
                    break;
            }
        }
    }

    //type=0是只下载不打开，type=1是下载完成后打开
    public void downloadFile(int downloadType) {
        if (FileUtils.fileExist(this, fileId)) {
            openFile();
        } else {
            this.downloadType = downloadType;
            String key = SharedPreferencesUtilsKt.getLongCache(this, ConstantKt.getKey_AllowMobileInternetDownload());
            if (NetworkUtils.isWifi(this) || TextUtils.equals(key, ConstantKt.getValue_true())) {
                mPresenter.downloadFile(articleId, fileId, fileName);
            } else {
                new WarnDialog.Builder().setInfo("当前设备处于非wifi网络环境下，下载该文件会产生较大流量是否打开流量开关下载")
                        .setBtnLeftText("取消").setBtnRightText("打开流量开关").setClickListener(new DialogMakeSureListener() {
                    @Override
                    public void makeSure(Dialog dialog) {
                        SharedPreferencesUtilsKt.saveLongCache(OpenFileActivity.this, ConstantKt.getKey_AllowMobileInternetDownload(), ConstantKt.getValue_true());
                        mPresenter.downloadFile(articleId, fileId, fileName);
                    }
                }).builder(this).show();
            }
        }

    }

    private void delete() {
        DeleteDialog deleteDialog = new DeleteDialog(this);
        deleteDialog.setFileId(fileId);
        deleteDialog.setType(DeleteDialog.FILE);
        deleteDialog.show();
    }

    private void rename() {
        RenameDialog renameDialog = new RenameDialog(this);
        renameDialog.setView(new EditText(this));
        renameDialog.setFileId(fileId);
        renameDialog.setType(DeleteDialog.FILE);
        renameDialog.show();
        KeyBoardUtils.showKeyBoard(this);
    }


    public void setTitle(String title) {
        binding.setTitle(title);
        if (notOpenFileFragment != null) {
            notOpenFileFragment.setFileName(title);
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMain(String text) {
        if (text.equals(FileUtils.DOWNLOAD_FINISH) && downloadType == 1) {
            openFile();
        }
    }

    private void openFile() {
        String path = SharedPreferencesUtilsKt.getLongCache(this, fileId);
        FileUtils.openFile(this, new File(path), fileSffix);
    }

}
