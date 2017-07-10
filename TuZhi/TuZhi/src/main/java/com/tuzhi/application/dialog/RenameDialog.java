package com.tuzhi.application.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.StyleRes;
import android.view.View;

import com.tuzhi.application.R;
import com.tuzhi.application.databinding.ViewRenameDialogBinding;
import com.tuzhi.application.moudle.enterpriseknowledge.knowledgedetails.mvp.KnowledgeDetailsActivity;
import com.tuzhi.application.moudle.enterpriseknowledge.knowledgedetails.openfile.mvp.OpenFileActivity;
import com.tuzhi.application.moudle.enterpriseknowledge.mvp.EnterpriseKnowledgeActivity;
import com.tuzhi.application.moudle.repository.mvp.RepositoryActivity;
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

public class RenameDialog extends AlertDialog {

    private static final String URL_LIB = "tzkm/knowledgeLib";
    private static final String URL_MOUDLE = "tzkm/editTitle";
    public static final String MOUDLE = "MOUDLE";
    public static final String LIB = "LIB";
    public static final String FILE = "FILE";

    private String type;
    private String libId;
    private String moudleId;
    private String fileId;
    private String text;

    private Context context;
    private ViewRenameDialogBinding binding;

    public void setLibId(String libId) {
        this.libId = libId;
    }

    public void setMoudleId(String moudleId) {
        this.moudleId = moudleId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public RenameDialog(Context context) {
        this(context, R.style.dialog);
    }

    public RenameDialog(Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = getLayoutInflater().inflate(R.layout.view_rename_dialog, null);
        setContentView(view);
        binding = DataBindingUtil.bind(view);
        binding.setDialog(this);
        binding.setName(text);
        binding.et.post(new Runnable() {
            @Override
            public void run() {
                if (text.length()>20){
                    binding.et.setSelection(20);
                }else{
                    binding.et.setSelection(text.length());
                }
            }
        });
        setCanceledOnTouchOutside(false);
    }

    public void cancel() {
        dismiss();
    }

    public void rename(String name) {
        switch (type) {
            case LIB:
                renameLib(name);
                break;
            case MOUDLE:
                renameMoudle(name);
                break;
            default:
                renameFile(name);
                break;
        }
    }

    private void renameFile(final String name) {
        WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(context);
        parameter.put("operate", "2");
        parameter.put("fileId", fileId);
        parameter.put("title", name);
        HttpUtilsKt.post(context, URL_MOUDLE, parameter, String.class, new HttpCallBack<String>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void onSuccess(@Nullable String s, @NotNull String text) {
                ToastUtilsKt.toast(context,"修改成功");
                OpenFileActivity activity = (OpenFileActivity) context;
                activity.setTitle(name);
                EventBus.getDefault().post(KnowledgeDetailsActivity.MESSAGE);
                dismiss();
            }

            @Override
            public void onFailure(@NotNull String text) {

            }
        });
    }

    private void renameLib(final String name) {
        WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(context);
        parameter.put("operate", "3");
        parameter.put("klId", libId);
        parameter.put("name", name);
        HttpUtilsKt.get(context, URL_LIB, parameter, String.class, new HttpCallBack<String>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void onSuccess(@Nullable String s, @NotNull String text) {
                ToastUtilsKt.toast(context,"修改成功");
                EnterpriseKnowledgeActivity activity = (EnterpriseKnowledgeActivity) context;
                activity.setTitle(name);
                EventBus.getDefault().post(RepositoryActivity.MESSAGE);
                dismiss();
            }

            @Override
            public void onFailure(@NotNull String text) {

            }
        });
    }


    private void renameMoudle(final String name) {
        WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(context);
        parameter.put("operate", "2");
        parameter.put("aId", moudleId);
        parameter.put("title", name);
        HttpUtilsKt.post(context, URL_MOUDLE, parameter, String.class, new HttpCallBack<String>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void onSuccess(@Nullable String s, @NotNull String text) {
                ToastUtilsKt.toast(context,"修改成功");
                KnowledgeDetailsActivity activity = (KnowledgeDetailsActivity) context;
                activity.setTitle(name);
                EventBus.getDefault().post(EnterpriseKnowledgeActivity.MESSAGE);
                dismiss();
            }

            @Override
            public void onFailure(@NotNull String text) {

            }
        });
    }
}
