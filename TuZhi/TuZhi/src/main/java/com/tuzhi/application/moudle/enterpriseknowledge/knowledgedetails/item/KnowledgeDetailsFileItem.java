package com.tuzhi.application.moudle.enterpriseknowledge.knowledgedetails.item;

import android.databinding.DataBindingUtil;
import android.view.View;

import com.tuzhi.application.R;
import com.tuzhi.application.databinding.ItemKnowledgeDetailsFileBinding;
import com.tuzhi.application.item.BaseItem;
import com.tuzhi.application.moudle.enterpriseknowledge.knowledgedetails.bean.KnowledgeDetailsListBean;
import com.tuzhi.application.utils.ImageUtils;

import org.jetbrains.annotations.NotNull;

/**
 * Created by wangpeng on 2017/6/12.
 */

public class KnowledgeDetailsFileItem extends BaseItem<KnowledgeDetailsListBean> {

    public static final String TYPE = "KnowledgeDetailsFileItem";

    private ItemKnowledgeDetailsFileBinding binding;

    @Override
    public void bindView(@NotNull View view) {
        binding = DataBindingUtil.bind(view);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_knowledge_details_file;
    }

    @Override
    public void handleData(KnowledgeDetailsListBean knowledgeDetailsListBean, int i) {
        binding.setData(knowledgeDetailsListBean);
        binding.executePendingBindings();
        int fileImage = ImageUtils.getFileImage(knowledgeDetailsListBean.getFileType(), 0);
        binding.iv.setImageResource(fileImage);
    }
}
