package com.tuzhi.application.dialog;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.view.View;

import com.tuzhi.application.R;
import com.tuzhi.application.databinding.DialogChooseOpennessBinding;

/**
 * Created by wangpeng on 2017/11/10.
 */

public class ChooseOpennessDialog extends BaseDialog {
    private boolean flag;
    private int type;
    private String[] libOpenOrSecret = {"公开", "私密", "企业所有人都可以访问此知识库", "只有加入的成员才能访问此知识库"};
    private String[] channelOpenOrSecret = {"公开", "私密", "企业所有人都可以访问此频道", "只有加入的成员才能访问此频道"};
    private String[] jurisdiction = {"管理员", "成员", "拥有该知识频道的所有权限", "只有操作知识卡片的权限"};

    public ChooseOpennessDialog(Context context, boolean flag, int type) {
        super(context);
        this.flag = flag;
        this.type = type;
    }

    @Override
    protected void init(ViewDataBinding bind) {
        DialogChooseOpennessBinding binding = (DialogChooseOpennessBinding) bind;
        binding.setDialog(this);
        binding.setFlag(flag);
        switch (type) {
            case 0:
                binding.setData(libOpenOrSecret);
                break;
            case 1:
                binding.setData(channelOpenOrSecret);
                break;
            case 2:
                binding.setData(jurisdiction);
                break;
        }


    }

    @Override
    protected int getLayout() {
        return R.layout.dialog_choose_openness;
    }

    @Override
    public void onViewClick(View view) {
        super.onViewClick(view);
    }
}
