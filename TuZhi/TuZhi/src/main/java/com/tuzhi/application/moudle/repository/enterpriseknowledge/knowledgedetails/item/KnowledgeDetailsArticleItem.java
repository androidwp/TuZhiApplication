package com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.item;

import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.tuzhi.application.R;
import com.tuzhi.application.databinding.ItemKnowledgeDetailsListArticleBinding;
import com.tuzhi.application.item.BaseItem;
import com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.bean.KnowledgeDetailsListBean;
import com.tuzhi.application.utils.CommonUtils;

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
        binding.setItem(this);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_knowledge_details_list_article;
    }

    @Override
    public void handleData(KnowledgeDetailsListBean knowledgeDetailsListBean, int i) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        if (!TextUtils.isEmpty(knowledgeDetailsListBean.getContent())) {
            boolean equals = TextUtils.equals(oldContent, knowledgeDetailsListBean.getContent());
            if ((isCreateWebview && !equals) || webView == null) {
                binding.fl.setVisibility(View.INVISIBLE);
                oldContent = knowledgeDetailsListBean.getContent();
                isCreateWebview = false;
                binding.fl.removeAllViews();
                webView = new WebView(context);
                webView.setBackgroundResource(R.color.colorGeneralBackground);
                webView.getSettings().setJavaScriptEnabled(true);
                binding.fl.addView(webView);
            }
            webView.addJavascriptInterface(new HeightGetter(), "jo");
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
                }

                @Override
                public void onPageFinished(final WebView view, String url) {
                    super.onPageFinished(view, url);
                    binding.fl.setVisibility(View.VISIBLE);
                    view.loadUrl("javascript:window.jo.run(document.documentElement.scrollHeight+'');");
                }
            });
        } else {
            binding.ll.setVisibility(View.GONE);
            layoutParams.setMargins(0, 0, 0, 0);
            binding.fl.setLayoutParams(layoutParams);
        }

    }

    public void unfold(View view) {
        view.setVisibility(View.GONE);
        binding.lineOne.setVisibility(View.GONE);
        binding.lineTwo.setVisibility(View.GONE);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        webView.setLayoutParams(layoutParams);
    }

    private class HeightGetter {
        @JavascriptInterface
        public void run(final String height) {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    if (Integer.parseInt(height) > CommonUtils.getScreenHeight(context)) {
                        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, CommonUtils.getScreenHeight(context) - 200);
                        webView.setLayoutParams(layoutParams);
                        binding.tvUnfold.setVisibility(View.VISIBLE);
                        binding.lineOne.setVisibility(View.VISIBLE);
                        binding.lineTwo.setVisibility(View.VISIBLE);
                    }
                }
            });
        }
    }
}
