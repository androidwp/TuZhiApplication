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
import com.tuzhi.application.moudle.enterpriseknowledge.mvp.EnterpriseKnowledgeActivity;
import com.tuzhi.application.moudle.repository.mvp.RepositoryActivity;
import com.tuzhi.application.utils.HttpCallBack;
import com.tuzhi.application.utils.HttpUtilsKt;

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

    private String type;
    private String libId;
    private String moudleId;
    private Context context;

    public String getLibId() {
        return libId;
    }

    public void setLibId(String libId) {
        this.libId = libId;
    }

    public String getMoudleId() {
        return moudleId;
    }

    public void setMoudleId(String moudleId) {
        this.moudleId = moudleId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public RenameDialog(Context context) {
        super(context);
    }

    public RenameDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
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
        ViewRenameDialogBinding binding = DataBindingUtil.bind(view);
        binding.setDialog(this);
        binding.setName("");
    }

    public void cancel() {
        dismiss();
    }

    public void rename(String name) {
        if (type.equals(LIB)) {
            renameLib(name);
        } else {
            renameMoudle(name);
        }
    }

    private void renameLib(final String name) {
        WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(getContext());
        parameter.put("operate", "3");
        parameter.put("klId", libId);
        parameter.put("name", name);
        HttpUtilsKt.get(context, URL_LIB, parameter, String.class, new HttpCallBack<String>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void onSuccess(@Nullable String s, @NotNull String text) {
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
        WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(getContext());
        parameter.put("operate", "2");
        parameter.put("aId", moudleId);
        parameter.put("title", name);
        HttpUtilsKt.post(context, URL_MOUDLE, parameter, String.class, new HttpCallBack<String>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void onSuccess(@Nullable String s, @NotNull String text) {
                KnowledgeDetailsActivity activity = (KnowledgeDetailsActivity) context;
                activity.setTitle(name);
                EventBus.getDefault().post(EnterpriseKnowledgeActivity.MESSAGE);
                dismiss();
                dismiss();
            }

            @Override
            public void onFailure(@NotNull String text) {

            }
        });
    }
}
