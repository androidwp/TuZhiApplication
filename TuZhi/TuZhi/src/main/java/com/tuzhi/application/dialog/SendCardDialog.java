package com.tuzhi.application.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.StyleRes;
import android.text.Html;
import android.view.View;

import com.tuzhi.application.R;
import com.tuzhi.application.databinding.DialogSendCardBinding;
import com.tuzhi.application.inter.OnDialogClickListener;
import com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.choosecolleague.bean.ChooseColleagueItemBean;

import java.util.ArrayList;

/**
 * Created by wangpeng on 2017/6/2.
 */

public class SendCardDialog extends AlertDialog {
    private DialogSendCardBinding binding;
    private OnDialogClickListener clickListener;
    private String text = "";
    private String title;
    private ArrayList<ChooseColleagueItemBean> arrayList = new ArrayList<>();

    public void setTitle(String title) {
        this.title = title;
    }

    public void setArrayList(ArrayList<ChooseColleagueItemBean> arrayList) {
        this.arrayList = arrayList;
    }

    public void setClickListener(OnDialogClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public SendCardDialog(Context context) {
        this(context, R.style.dialog);
    }

    public SendCardDialog(Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = getLayoutInflater().inflate(R.layout.dialog_send_card, null);
        setContentView(view);
        binding = DataBindingUtil.bind(view);
        binding.setDialog(this);
        binding.setTitle("[知识卡片]" + title);
        binding.setPhotoList(arrayList);
        if (arrayList.size() > 4)
            binding.setNumberOfPeople(Html.fromHtml("等<font color='#459ff9'> " + arrayList.size() + " </font>人"));
        setCanceledOnTouchOutside(false);
    }

    public void cancel() {
        dismiss();
    }

    public void rename(View view, String text) {
        if (clickListener != null) {
            view.setTag(text);
            clickListener.onDialogClick(view);
        }
    }
}
