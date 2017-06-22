package com.tuzhi.application.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.StyleRes;
import android.view.View;

import com.tuzhi.application.R;
import com.tuzhi.application.databinding.ViewDeleteDialogBinding;
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

public class DeleteDialog extends AlertDialog {

    private static final String URL_LIB = "tzkm/knowledgeLib";
    private static final String URL_MOUDLE = "tzkm/editTitle";

    public static final String MOUDLE = "MOUDLE";
    public static final String LIB = "LIB";

    private String type;
    private String libId;
    private String moudleId;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

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

    private Context context;

    public DeleteDialog(Context context) {
        super(context);
    }

    public DeleteDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
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
        if (type.equals(LIB)) {
            deleteLib();
        } else {
            deleteMoudle();
        }
    }

    private void deleteLib() {
        WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(getContext());
        parameter.put("operate", "4");
        parameter.put("klId", libId);
        HttpUtilsKt.get(context, URL_LIB, parameter, String.class, new HttpCallBack<String>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void onSuccess(@Nullable String s, @NotNull String text) {
                EnterpriseKnowledgeActivity activity = (EnterpriseKnowledgeActivity) context;
                activity.back();
                EventBus.getDefault().post(RepositoryActivity.MESSAGE);
                dismiss();
            }

            @Override
            public void onFailure(@NotNull String text) {

            }
        });
    }


    private void deleteMoudle() {
        WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(getContext());
        parameter.put("operate", "3");
        parameter.put("aId", moudleId);
        HttpUtilsKt.post(context, URL_MOUDLE, parameter, String.class, new HttpCallBack<String>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void onSuccess(@Nullable String s, @NotNull String text) {
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
