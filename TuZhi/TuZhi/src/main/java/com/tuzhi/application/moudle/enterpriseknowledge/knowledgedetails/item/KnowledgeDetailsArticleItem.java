package com.tuzhi.application.moudle.enterpriseknowledge.knowledgedetails.item;

import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.tuzhi.application.R;
import com.tuzhi.application.databinding.ItemKnowledgeDetailsListArticleBinding;
import com.tuzhi.application.item.BaseItem;
import com.tuzhi.application.moudle.enterpriseknowledge.knowledgedetails.bean.KnowledgeDetailsListBean;

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
        if (!TextUtils.isEmpty(knowledgeDetailsListBean.getContent())) {
            binding.fl.setVisibility(View.INVISIBLE);
            boolean equals = TextUtils.equals(oldContent, knowledgeDetailsListBean.getContent());
            if ((isCreateWebview && !equals) || webView == null) {
                oldContent = knowledgeDetailsListBean.getContent();
                isCreateWebview = false;
                binding.fl.removeAllViews();
                webView = new WebView(context);
                webView.setBackgroundResource(R.color.colorGeneralBackground);
                webView.getSettings().setJavaScriptEnabled(true);
                binding.fl.addView(webView);
            }
            webView.loadUrl(knowledgeDetailsListBean.getViewContentUrl());
            webView.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView webView, String s) {
                    webView.loadUrl(s);
                    return true;
                }

                @Override
                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                    super.onPageStarted(view, url, favicon);
                    binding.fl.setVisibility(View.INVISIBLE);
                }

                @Override
                public void onPageFinished(WebView view, String url) {
                    super.onPageFinished(view, url);
                    binding.fl.setVisibility(View.VISIBLE);
                }
            });
        } else {
            binding.fl.setVisibility(View.GONE);
            layoutParams.setMargins(0, 0, 0, 0);
            binding.fl.setLayoutParams(layoutParams);
        }

    }
}
