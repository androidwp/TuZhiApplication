package com.tuzhi.application.moudle.enterpriseknowledge.knowledgedetails.openfile.mvp;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.widget.EditText;

import com.tuzhi.application.R;
import com.tuzhi.application.databinding.ActivityOpenFileBinding;
import com.tuzhi.application.dialog.DeleteDialog;
import com.tuzhi.application.dialog.RenameDialog;
import com.tuzhi.application.moudle.basemvp.MVPBaseActivity;
import com.tuzhi.application.moudle.enterpriseknowledge.knowledgedetails.openfile.fragment.NotOpenFileFragment;
import com.tuzhi.application.moudle.enterpriseknowledge.knowledgedetails.openfile.fragment.OpenFileFragment;
import com.tuzhi.application.utils.KeyBoardUtils;
import com.tuzhi.application.view.ActionSheet;

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
    //文件id
    public static final String ID = "ID";
    //文件下载地址
    public static final String FILE_URL = "FILE_URL";
    //文件大小
    public static final String FILE_SIZE = "FILE_SIZE";
    public static final String TYPE_CAN_OPEN = "TYPE_CAN_OPEN";
    public static final String TYPE_NOT_OPEN = "TYPE_NOT_OPEN";
    public static final String CONTENT = "CONTENT";

    private ActionSheet actionSheet;
    private String type;
    private ActivityOpenFileBinding binding;
    private String fileId;
    private String fileUrl;
    private String fileName;
    private String fileSize;
    private NotOpenFileFragment notOpenFileFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_open_file;
    }

    @Override
    protected void init(ViewDataBinding viewDataBinding) {
        type = getIntent().getStringExtra(TYPE);
        fileId = getIntent().getStringExtra(ID);
        fileUrl = getIntent().getStringExtra(FILE_URL);
        fileName = getIntent().getStringExtra(FILE_NAME);
        fileSize = getIntent().getStringExtra(FILE_SIZE);
        binding = (ActivityOpenFileBinding) viewDataBinding;
        binding.setActivity(this);
        binding.setTitle(fileName);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (type.equals(TYPE_CAN_OPEN)) {
            ArrayList<String> images = getIntent().getStringArrayListExtra(CONTENT);
            OpenFileFragment openFileFragment = new OpenFileFragment();
            Bundle bundle = new Bundle();
            bundle.putStringArrayList(CONTENT, images);
            openFileFragment.setArguments(bundle);
            fragmentTransaction.add(R.id.fl, openFileFragment).commit();
        } else {
            notOpenFileFragment = new NotOpenFileFragment();
            Bundle bundle = new Bundle();
            bundle.putString(ID, fileId);
            bundle.putString(FILE_URL, fileUrl);
            bundle.putString(FILE_NAME, fileName);
            bundle.putString(FILE_SIZE, fileSize);
            notOpenFileFragment.setArguments(bundle);
            fragmentTransaction.add(R.id.fl, notOpenFileFragment).commit();
        }
    }

    public void back() {
        onBackPressed();
    }

    //type=0  为删除  重命名，type=1  为选择图库
    public void openSelectDialog() {
        ActionSheet.Builder builder = ActionSheet.createBuilder(this, getSupportFragmentManager())
                .setCancelButtonTitle("取消")
                .setCancelableOnTouchOutside(true);
        if (type.equals(TYPE_CAN_OPEN)) {
            builder.setOtherButtonTitles("下载并用第三方应用打开", "重命名", "删除");
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
        if (type.equals(TYPE_CAN_OPEN)) {
            switch (index) {
                case 0:

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

}
