package com.tuzhi.application.moudle.enterpriseknowledge.knowledgedetails.item;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.view.View;

import com.tuzhi.application.R;
import com.tuzhi.application.databinding.ItemKnowledgeDetailsCommentBinding;
import com.tuzhi.application.item.BaseItem;
import com.tuzhi.application.moudle.enterpriseknowledge.knowledgedetails.bean.KnowledgeDetailsListBean;
import com.tuzhi.application.moudle.enterpriseknowledge.knowledgedetails.commentlist.mvp.CommentListActivity;
import com.tuzhi.application.utils.HttpCallBack;
import com.tuzhi.application.utils.HttpUtilsKt;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.WeakHashMap;

/**
 * Created by wangpeng on 2017/6/13.
 */

public class KnowledgeDetailsCommentItem extends BaseItem<KnowledgeDetailsListBean> {

    public static final String TYPE = "KnowledgeDetailsCommentItem";
    private static final String URL = "tzkm/praise";

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

    public void skipCommentListActivity(String aid, String cid) {
        Intent intent = new Intent(context, CommentListActivity.class);
        intent.putExtra(CommentListActivity.AID, aid);
        intent.putExtra(CommentListActivity.CID, cid);
        context.startActivity(intent);
    }

    public void clickPraise(final KnowledgeDetailsListBean knowledgeDetailsListBean) {
        if (!knowledgeDetailsListBean.isPraiseStatus()) {
            WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(context);
            parameter.put("falg", "1");
            parameter.put("oType", "1");
            parameter.put("cType", "1");
            parameter.put("oId", knowledgeDetailsListBean.getAid());
            parameter.put("cId", knowledgeDetailsListBean.getCid());
            HttpUtilsKt.post(context, URL, parameter, String.class, new HttpCallBack<String>() {
                @Override
                public void onFinish() {

                }

                @Override
                public void onSuccess(@Nullable String s, @NotNull String text) {
                    knowledgeDetailsListBean.setPraiseStatus(true);
                    int praiseNumber = Integer.parseInt(knowledgeDetailsListBean.getPraiseNumber());
                    knowledgeDetailsListBean.setPraiseNumber((praiseNumber + 1) + "");
                }

                @Override
                public void onFailure(@NotNull String text) {

                }
            });
        }
    }


}
