package com.tuzhi.application.moudle.enterpriseknowledge.knowledgedetails.item;

import android.databinding.DataBindingUtil;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.tuzhi.application.R;
import com.tuzhi.application.databinding.ItemKnowledgeDetailsListArticleBinding;
import com.tuzhi.application.item.BaseItem;
import com.tuzhi.application.moudle.enterpriseknowledge.knowledgedetails.bean.KnowledgeDetailsListBean;
import com.tuzhi.application.utils.CommonUtils;
import com.tuzhi.application.utils.LogUtilsKt;

import org.jetbrains.annotations.NotNull;

/**
 * Created by wangpeng on 2017/6/9.
 */

public class KnowledgeDetailsArticleItem extends BaseItem<KnowledgeDetailsListBean> {

    public static boolean isCreateWebview = true;
    public static String oldContent;

    public static final String TYPE = "KnowledgeDetailsArticleItem";

    private ItemKnowledgeDetailsListArticleBinding binding;
    private WebView webView;

    @Override
    public void bindView(@NotNull View view) {
        binding = DataBindingUtil.bind(view);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_knowledge_details_list_article;
    }

    @Override
    public void handleData(KnowledgeDetailsListBean knowledgeDetailsListBean, int i) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        int dimens = (int) CommonUtils.getDimens(context, R.dimen.generalSpace);
        if (!TextUtils.isEmpty(knowledgeDetailsListBean.getContent())) {
            binding.fl.setVisibility(View.VISIBLE);
            boolean equals = TextUtils.equals(oldContent, knowledgeDetailsListBean.getContent());
            if ((isCreateWebview && !equals) || webView == null) {
                oldContent = knowledgeDetailsListBean.getContent();
                isCreateWebview = false;
                binding.fl.removeAllViews();
                webView = new WebView(context);
                WebSettings settings = webView.getSettings();
                webView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                webView.setPadding(dimens, dimens, dimens, dimens);
                layoutParams.setMargins(dimens, dimens, dimens, dimens);
                binding.fl.setLayoutParams(layoutParams);
                binding.fl.addView(webView);
            }
            LogUtilsKt.showLog("TAG",knowledgeDetailsListBean.getContent());
            webView.loadDataWithBaseURL(null, knowledgeDetailsListBean.getContent(), "text/html", "utf-8", null);
            webView.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView webView, String s) {
                    webView.loadDataWithBaseURL(null, s, "text/html", "utf-8", null);
                    return true;
                }
            });
        } else {
            binding.fl.setVisibility(View.GONE);
            layoutParams.setMargins(0, 0, 0, dimens);
            binding.fl.setLayoutParams(layoutParams);
        }

    }
}
