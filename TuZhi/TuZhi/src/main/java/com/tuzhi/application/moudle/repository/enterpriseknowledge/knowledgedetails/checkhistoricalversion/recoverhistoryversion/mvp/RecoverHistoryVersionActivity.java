package com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.checkhistoricalversion.recoverhistoryversion.mvp;


import android.databinding.ViewDataBinding;
import android.graphics.Bitmap;
import android.view.View;

import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.tuzhi.application.R;
import com.tuzhi.application.databinding.ActivityRecoverHistoryVersionBinding;
import com.tuzhi.application.moudle.basemvp.MVPBaseActivity;
import com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.mvp.KnowledgeDetailsActivity;
import com.tuzhi.application.utils.ToastUtilsKt;

import org.greenrobot.eventbus.EventBus;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class RecoverHistoryVersionActivity extends MVPBaseActivity<RecoverHistoryVersionContract.View, RecoverHistoryVersionPresenter> implements RecoverHistoryVersionContract.View {

    public static final String ID = "ID";
    public static final String AID = "AID";

    private ActivityRecoverHistoryVersionBinding binding;
    private String id;
    private String aid;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_recover_history_version;
    }

    @Override
    protected void init(ViewDataBinding viewDataBinding) {
        id = getIntent().getStringExtra(ID);
        aid = getIntent().getStringExtra(AID);
        binding = (ActivityRecoverHistoryVersionBinding) viewDataBinding;
        binding.setActivity(this);
        binding.wv.getSettings().setJavaScriptEnabled(true);
        binding.wv.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String s) {
                webView.loadUrl(s);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                binding.wv.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                binding.wv.setVisibility(View.VISIBLE);
            }
        });
        mPresenter.downloadData(aid, id);
    }

    public void back() {
        onBackPressed();
    }

    public void recoverVersion() {
        mPresenter.recoverHistory(aid, id);
    }

    @Override
    public void downloadSuccess(String title, String url) {
        binding.setTitle(title);
        binding.wv.loadUrl(url);
    }

    @Override
    public void recoverSuccess() {
        ToastUtilsKt.toast(this, "恢复成功");
        EventBus.getDefault().post(KnowledgeDetailsActivity.MESSAGE);
    }
}
