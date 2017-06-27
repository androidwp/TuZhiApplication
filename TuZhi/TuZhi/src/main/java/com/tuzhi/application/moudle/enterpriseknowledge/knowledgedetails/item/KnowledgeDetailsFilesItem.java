package com.tuzhi.application.moudle.enterpriseknowledge.knowledgedetails.item;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.tuzhi.application.R;
import com.tuzhi.application.item.BaseItem;
import com.tuzhi.application.moudle.enterpriseknowledge.knowledgedetails.bean.KnowledgeDetailsListBean;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * Created by wangpeng on 2017/6/12.
 */

public class KnowledgeDetailsFilesItem extends BaseItem<KnowledgeDetailsListBean> {

    public static final String TYPE = "KnowledgeDetailsFilesItem";
    private LinearLayout ll;

    @Override
    public void bindView(@NotNull View view) {
        ll = (LinearLayout) view.findViewById(R.id.ll);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_knowledge_details_files;
    }

    @Override
    public void handleData(KnowledgeDetailsListBean knowledgeDetailsListBean, int i) {
        ArrayList<KnowledgeDetailsListBean> files = knowledgeDetailsListBean.getFiles();
        ll.removeAllViews();
        for (KnowledgeDetailsListBean bean : files) {
            KnowledgeDetailsFileItem item = new KnowledgeDetailsFileItem();
            View view = LayoutInflater.from(context).inflate(item.getLayoutResId(), null);
            item.bindViews(view);
            item.bindView(view);
            item.handleData(bean, 0);
            ll.addView(view);
        }
    }
}
