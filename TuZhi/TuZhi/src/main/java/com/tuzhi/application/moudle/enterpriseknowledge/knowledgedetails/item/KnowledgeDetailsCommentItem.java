package com.tuzhi.application.moudle.enterpriseknowledge.knowledgedetails.item;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.view.View;

import com.tuzhi.application.R;
import com.tuzhi.application.databinding.ItemKnowledgeDetailsCommentBinding;
import com.tuzhi.application.item.BaseItem;
import com.tuzhi.application.moudle.enterpriseknowledge.knowledgedetails.bean.KnowledgeDetailsListBean;
import com.tuzhi.application.moudle.enterpriseknowledge.knowledgedetails.commentlist.mvp.CommentListActivity;

import org.jetbrains.annotations.NotNull;

/**
 * Created by wangpeng on 2017/6/13.
 */

public class KnowledgeDetailsCommentItem extends BaseItem<KnowledgeDetailsListBean> {

    public static final String TYPE="KnowledgeDetailsCommentItem";

    private ItemKnowledgeDetailsCommentBinding binding;

    @Override
    public void bindView(@NotNull View view) {
        binding = DataBindingUtil.bind(view);
        binding.setItem(this);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_knowledge_details_comment;
    }

    @Override
    public void handleData(KnowledgeDetailsListBean knowledgeDetailsListBean, int i) {
        binding.setData(knowledgeDetailsListBean);
        binding.executePendingBindings();
    }

    public void skipCommentListActivity(String aid,String cid){
        Intent intent=new Intent(context, CommentListActivity.class);
        intent.putExtra(CommentListActivity.AID,aid);
        intent.putExtra(CommentListActivity.CID,cid);
        context.startActivity(intent);
    }
}
