package com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.commentlist.item;

import android.databinding.DataBindingUtil;
import android.view.View;

import com.tuzhi.application.R;
import com.tuzhi.application.databinding.ItemCommentListBinding;
import com.tuzhi.application.item.BaseItem;
import com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.commentlist.bean.CommentListBean;

import org.jetbrains.annotations.NotNull;

/**
 * Created by wangpeng on 2017/6/14.
 */

public class CommentListItem extends BaseItem<CommentListBean> {

    public static final String TYPE="CommentListItem";

    private ItemCommentListBinding binding;

    @Override
    public void bindView(@NotNull View view) {
        binding = DataBindingUtil.bind(view);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_comment_list;
    }

    @Override
    public void handleData(CommentListBean commentListBean, int i) {
        binding.setData(commentListBean);
    }
}
