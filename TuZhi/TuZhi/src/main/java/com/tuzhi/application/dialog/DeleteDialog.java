package com.tuzhi.application.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.StyleRes;
import android.view.View;

import com.tuzhi.application.R;
import com.tuzhi.application.databinding.ViewDeleteDialogBinding;
import com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.mvp.KnowledgeDetailsActivity;
import com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.openfile.mvp.OpenFileActivity;
import com.tuzhi.application.moudle.repository.enterpriseknowledge.mvp.EnterpriseKnowledgeActivity;
import com.tuzhi.application.moudle.repository.mvp.RepositoryFragment;
import com.tuzhi.application.utils.HttpCallBack;
import com.tuzhi.application.utils.HttpUtilsKt;
import com.tuzhi.application.utils.ToastUtilsKt;

import org.greenrobot.eventbus.EventBus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.WeakHashMap;

/**
 * Created by wangpeng on 2017/6/2.
 */

public class DeleteDialog extends AlertDialog {

    private static final String URL_LIB = "tzkm/knowledgeLib";
    private static final String URL_MOUDLE = "tzkm/editTitle";

    public static final String MOUDLE = "MOUDLE";
    public static final String LIB = "LIB";
    public static final String FILE = "FILE";


    private String type;
    private String libId;
    private String moudleId;
    private String fileId;


    public void setType(String type) {
        this.type = type;
    }

    public void setLibId(String libId) {
        this.libId = libId;
    }

    public void setMoudleId(String moudleId) {
        this.moudleId = moudleId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    private Context context;

    public DeleteDialog(Context context) {
        this(context, R.style.dialog);
    }


    public DeleteDialog(Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = getLayoutInflater().inflate(R.layout.view_delete_dialog, null);
        setContentView(view);
        ViewDeleteDialogBinding binding = DataBindingUtil.bind(view);
        binding.setDialog(this);
    }

    public void cancel() {
        dismiss();
    }

    public void delete() {
        switch (type) {
            case LIB:
                deleteLib();
                break;
            case MOUDLE:
                deleteMoudle();
                break;
            default:
                deleteFile();
                break;
        }
    }

    private void deleteFile() {
        WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(context);
        parameter.put("operate", "3");
        parameter.put("fileId", fileId);
        HttpUtilsKt.post(context, URL_MOUDLE, parameter, String.class, new HttpCallBack<String>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void onSuccess(@Nullable String s, @NotNull String text) {
                ToastUtilsKt.toast(context,"删除成功");
                OpenFileActivity activity = (OpenFileActivity) context;
                activity.back();
                EventBus.getDefault().post(KnowledgeDetailsActivity.MESSAGE);
                dismiss();
            }

            @Override
            public void onFailure(@NotNull String text) {

            }
        });
    }

    private void deleteLib() {
        WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(context);
        parameter.put("operate", "4");
        parameter.put("klId", libId);
        HttpUtilsKt.get(context, URL_LIB, parameter, String.class, new HttpCallBack<String>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void onSuccess(@Nullable String s, @NotNull String text) {
                ToastUtilsKt.toast(context,"删除成功");
                EnterpriseKnowledgeActivity activity = (EnterpriseKnowledgeActivity) context;
                activity.back();
                EventBus.getDefault().post(RepositoryFragment.MESSAGE);
                dismiss();
            }

            @Override
            public void onFailure(@NotNull String text) {

            }
        });
    }


    private void deleteMoudle() {
        WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(context);
        parameter.put("operate", "3");
        parameter.put("aId", moudleId);
        HttpUtilsKt.post(context, URL_MOUDLE, parameter, String.class, new HttpCallBack<String>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void onSuccess(@Nullable String s, @NotNull String text) {
                ToastUtilsKt.toast(context,"删除成功");
                KnowledgeDetailsActivity activity = (KnowledgeDetailsActivity) context;
                activity.back();
                EventBus.getDefault().post(EnterpriseKnowledgeActivity.MESSAGE);
                dismiss();
            }

            @Override
            public void onFailure(@NotNull String text) {

            }
        });
    }
}
