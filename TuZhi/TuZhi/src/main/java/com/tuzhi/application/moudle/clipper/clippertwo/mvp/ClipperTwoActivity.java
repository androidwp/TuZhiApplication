package com.tuzhi.application.moudle.clipper.clippertwo.mvp;


import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.EditText;

import com.tuzhi.application.R;
import com.tuzhi.application.databinding.ActivityClipperTwoBinding;
import com.tuzhi.application.dialog.RenameDialog;
import com.tuzhi.application.inter.ItemClickListener;
import com.tuzhi.application.inter.OnDialogClickListener;
import com.tuzhi.application.item.GeneralLoadFootViewItem;
import com.tuzhi.application.moudle.basemvp.MVPBaseActivity;
import com.tuzhi.application.moudle.clipper.mvp.ClipperOneActivity;
import com.tuzhi.application.moudle.repository.knowledgachannel.bean.KnowledgeChannelItemBean;
import com.tuzhi.application.moudle.repository.knowledgachannel.item.KnowledgeChannelItem;
import com.tuzhi.application.utils.KeyBoardUtils;
import com.tuzhi.application.utils.ToastUtilsKt;
import com.tuzhi.application.view.LoadMoreListener;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import kale.adapter.CommonRcvAdapter;
import kale.adapter.item.AdapterItem;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class ClipperTwoActivity extends MVPBaseActivity<ClipperTwoContract.View, ClipperTwoPresenter> implements ClipperTwoContract.View, SwipeRefreshLayout.OnRefreshListener, LoadMoreListener, ItemClickListener, OnDialogClickListener {
    public static final String ID = "id";
    public static final String ARTICLE_URL = "ARTICLE_URL";
    private String id;
    private ArrayList<KnowledgeChannelItemBean> mData = new ArrayList<>();
    private ActivityClipperTwoBinding binding;
    private String articleUrl;
    private KnowledgeChannelItemBean knowledgeChannelItemBean;
    private RenameDialog createDialog;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_clipper_two;
    }

    @Override
    protected void init(ViewDataBinding viewDataBinding) {
        id = getIntent().getStringExtra(ID);
        articleUrl = getIntent().getStringExtra(ARTICLE_URL);
        binding = (ActivityClipperTwoBinding) viewDataBinding;
        binding.setActivity(this);
        binding.rrv.isShowRefreshView(true);
        binding.rrv.setOnRefreshListener(this);
        binding.rrv.setLoadListener(this);
        CommonRcvAdapter<KnowledgeChannelItemBean> adapter = new CommonRcvAdapter<KnowledgeChannelItemBean>(mData) {
            @NonNull
            @Override
            public AdapterItem createItem(Object o) {
                String itemType = (String) o;
                switch (itemType) {
                    case KnowledgeChannelItem.TYPE:
                        KnowledgeChannelItem knowledgeChannelItem = new KnowledgeChannelItem();
                        knowledgeChannelItem.setClickListener(ClipperTwoActivity.this);
                        return knowledgeChannelItem;
                    default:
                        return new GeneralLoadFootViewItem();
                }
            }

            @Override
            public Object getItemType(KnowledgeChannelItemBean enterpriseKnowledgeListItemBean) {
                return enterpriseKnowledgeListItemBean.getItemType();
            }
        };
        binding.rrv.setAdapter(adapter);
    }

    @Override
    public void onRefresh() {
        mPresenter.downLoadData(id, 0);
    }

    @Override
    public void loadMoreListener(int page) {
        mPresenter.downLoadData(id, page);
    }

    @Override
    public void onItemClick(View view) {
        knowledgeChannelItemBean = (KnowledgeChannelItemBean) view.getTag();
        createDialog = new RenameDialog(this);
        createDialog.setView(new EditText(this));
        createDialog.setConfirmText("确定");
        createDialog.setTitle("新建知识卡片");
        createDialog.setClickListener(this);
        createDialog.show();
        KeyBoardUtils.showKeyBoard(this);
    }

    public void back() {
        onBackPressed();
    }

    @Override
    public void downloadFinish(int page, boolean haveNextPage, ArrayList<KnowledgeChannelItemBean> data) {
        binding.rrv.downLoadFinish(page, haveNextPage, mData, data, false);
    }

    @Override
    public void downloadFinishNothing() {
        binding.rrv.isShowRefreshView(false);
    }

    @Override
    public void createCardSuccess() {
        createDialog.dismiss();
        ToastUtilsKt.toast(this, "创建成功");
        EventBus.getDefault().post(ClipperOneActivity.EVENT_BACK);
        onBackPressed();
    }

    @Override
    public void onDialogClick(View view) {
        switch (view.getId()) {
            case R.id.tvRename:
                mPresenter.createCard(knowledgeChannelItemBean.getKlid(), knowledgeChannelItemBean.getKcid(), (String) view.getTag(), articleUrl);
                break;
        }
    }
}
