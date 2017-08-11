package com.tuzhi.application.moudle.repository.enterpriseknowledge.item;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.view.View;
import android.widget.ImageView;

import com.tuzhi.application.R;
import com.tuzhi.application.databinding.ItemEnterpriseKnowledgeListBinding;
import com.tuzhi.application.item.BaseItem;
import com.tuzhi.application.moudle.repository.enterpriseknowledge.bean.KnowledgeCardItemBean;
import com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.mvp.KnowledgeDetailsActivity;
import com.tuzhi.application.utils.HttpCallBack;
import com.tuzhi.application.utils.HttpUtilsKt;
import com.tuzhi.application.utils.ImageUtils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

/**
 * Created by wangpeng on 2017/6/2.
 */

public class EnterpriseKnowledgeListItem extends BaseItem<KnowledgeCardItemBean> {

    public static final String TYPE = "EnterpriseKnowledgeListItem";
    private static final String URL = "tzkm/praise";
    private ItemEnterpriseKnowledgeListBinding binding;
    private List<ImageView> imageViewList = new ArrayList<>();

    @Override
    public void bindView(@NotNull View view) {
        binding = DataBindingUtil.bind(view);
        imageViewList.add(binding.rivOne);
        imageViewList.add(binding.rivTwo);
        imageViewList.add(binding.rivThree);
        imageViewList.add(binding.rivFore);
        imageViewList.add(binding.rivFive);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_enterprise_knowledge_list;
    }

    @Override
    public void handleData(KnowledgeCardItemBean knowledgeCardItemBean, int i) {
        binding.setData(knowledgeCardItemBean);
        binding.setItem(this);
        binding.executePendingBindings();

        List<String> joinPortraits = knowledgeCardItemBean.getJoinPortraits();
        for (ImageView imageView : imageViewList) {
            imageView.setVisibility(View.GONE);
        }
        if (joinPortraits != null) {
            for (int j = 0; j < joinPortraits.size(); j++) {
                String url = joinPortraits.get(j);
                ImageView imageView = imageViewList.get(j);
                imageView.setVisibility(View.VISIBLE);
                ImageUtils.loadImage(imageView, url);
            }
        }
    }

    public void skipKnowledgeDetailsActivity(String title, String id) {
        Intent intent = new Intent(context, KnowledgeDetailsActivity.class);
        intent.putExtra(KnowledgeDetailsActivity.ID, id);
        intent.putExtra(KnowledgeDetailsActivity.TITLE, title);
        context.startActivity(intent);
    }

    public void clickPraise(final KnowledgeCardItemBean KnowledgeCardItemBean) {
        if (!KnowledgeCardItemBean.isPraiseStatus()) {
            WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(context);
            parameter.put("falg", "1");
            parameter.put("oType", "1");
            parameter.put("oId", KnowledgeCardItemBean.getId());
            HttpUtilsKt.post(context, URL, parameter, String.class, new HttpCallBack<String>() {
                @Override
                public void onFinish() {

                }

                @Override
                public void onSuccess(@Nullable String s, @NotNull String text) {
                    KnowledgeCardItemBean.setPraiseStatus(true);
                    int praiseNumber = Integer.parseInt(KnowledgeCardItemBean.getPraiseNumber());
                    KnowledgeCardItemBean.setPraiseNumber((praiseNumber + 1) + "");
                }

                @Override
                public void onFailure(@NotNull String text) {

                }
            });
        }
    }
}
