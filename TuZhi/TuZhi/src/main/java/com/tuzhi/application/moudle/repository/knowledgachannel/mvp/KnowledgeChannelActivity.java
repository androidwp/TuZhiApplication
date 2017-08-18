package com.tuzhi.application.moudle.repository.knowledgachannel.mvp;


import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.tuzhi.application.R;
import com.tuzhi.application.databinding.ActivityKnowledgeChannelBinding;
import com.tuzhi.application.dialog.DeleteDialog;
import com.tuzhi.application.dialog.RenameDialog;
import com.tuzhi.application.inter.OnDialogClickListener;
import com.tuzhi.application.item.GeneralLoadFootViewItem;
import com.tuzhi.application.moudle.basemvp.MVPBaseActivity;
import com.tuzhi.application.moudle.repository.crepository.mvp.CrepositoryActivity;
import com.tuzhi.application.moudle.repository.knowledgachannel.bean.KnowledgeChannelItemBean;
import com.tuzhi.application.moudle.repository.knowledgachannel.item.KnowledgeChannelItem;
import com.tuzhi.application.moudle.repository.mvp.RepositoryFragment;
import com.tuzhi.application.utils.ConstantKt;
import com.tuzhi.application.utils.KeyBoardUtils;
import com.tuzhi.application.utils.ToastUtilsKt;
import com.tuzhi.application.view.ActionSheet;
import com.tuzhi.application.view.LoadMoreListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import kale.adapter.CommonRcvAdapter;
import kale.adapter.item.AdapterItem;


/**
 * MVPPlugin
 */

public class KnowledgeChannelActivity extends MVPBaseActivity<KnowledgeChannelContract.View, KnowledgeChannelPresenter> implements KnowledgeChannelContract.View, SwipeRefreshLayout.OnRefreshListener, LoadMoreListener, ActionSheet.ActionSheetListener, OnDialogClickListener {
    public static final String MESSAGE = "KnowledgeChannelActivity_refresh";
    public static final String ID = "id";
    public static final String TITLE = "TITLE";
    private String listId;
    private ArrayList<KnowledgeChannelItemBean> mData = new ArrayList<>();
    private ActivityKnowledgeChannelBinding binding;
    private ActionSheet actionSheet;
    private String title;
    private DeleteDialog deleteDialog;
    private RenameDialog renameDialog;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_knowledge_channel;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(String event) {
        if (TextUtils.equals(event, MESSAGE)) {
            onRefresh();
        }
    }

    @Override
    protected void init(ViewDataBinding viewDataBinding) {
        EventBus.getDefault().register(this);
        binding = (ActivityKnowledgeChannelBinding) viewDataBinding;
        listId = getIntent().getStringExtra(ID);
        title = getIntent().getStringExtra(TITLE);
        setTitle(title);
        binding.setActivity(this);
        binding.rrv.isShowRefreshView(true);
        binding.rrv.setOnRefreshListener(this);
        binding.rrv.setLoadListener(this);
        binding.rrv.setTitle("该知识库下还没有频道哦");
        binding.rrv.setInfo("点击上方的\"+\"号，创建频道");
        CommonRcvAdapter<KnowledgeChannelItemBean> adapter = new CommonRcvAdapter<KnowledgeChannelItemBean>(mData) {
            @NonNull
            @Override
            public AdapterItem createItem(Object o) {
                String itemType = (String) o;
                switch (itemType) {
                    case KnowledgeChannelItem.TYPE:
                        return new KnowledgeChannelItem();
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


    public void back() {
        onBackPressed();
    }

    public void skipCreateRepositoryActivity() {
        Intent intent = new Intent(this, CrepositoryActivity.class);
        intent.putExtra(CrepositoryActivity.TYPE, CrepositoryActivity.CHANNEL);
        intent.putExtra(CrepositoryActivity.ID, listId);
        startActivityForResult(intent, ConstantKt.getCREATE_CODE());
    }

    public void openMenu() {
        actionSheet = ActionSheet.createBuilder(this, getSupportFragmentManager())
                .setCancelButtonTitle("取消")
                .setOtherButtonTitles("重命名知识库", "删除知识库")
                .setCancelableOnTouchOutside(true)
                .setListener(this).show();
    }

    @Override
    public void onRefresh() {
        mPresenter.downLoadData(listId, 0);
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
    public void loadMoreListener(int page) {
        mPresenter.downLoadData(listId, page);
    }

    @Override
    public void onBackPressed() {
        if (actionSheet != null && !actionSheet.ismDismissed()) {
            actionSheet.dismiss();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onDismiss(ActionSheet actionSheet, boolean isCancel) {
        actionSheet.dismiss();
    }

    @Override
    public void onOtherButtonClick(ActionSheet actionSheet, int index) {
        if (index == 0) {
            renameDialog = new RenameDialog(this, R.style.dialog);
            renameDialog.setView(new EditText(this));
            renameDialog.setText(title);
            renameDialog.setClickListener(this);
            renameDialog.show();
            KeyBoardUtils.showKeyBoard(this);
        } else {
            deleteDialog = new DeleteDialog(this, R.style.dialog);
            deleteDialog.setText("你确定要删除该知识库吗？删除后将无法恢复。");
            deleteDialog.setClickListener(this);
            deleteDialog.show();
        }
        actionSheet.dismiss();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ConstantKt.getCREATE_CODE() && resultCode == ConstantKt.getCREATE_CODE()) {
            binding.rrv.isShowRefreshView(true);
        }
    }

    public void setTitle(String title) {
        this.title = title;
        binding.tvTitle.setText(title);
    }

    @Override
    public void deleteLibSuccess() {
        deleteDialog.dismiss();
        ToastUtilsKt.toast(this, "删除成功");
        //通知刷新
        EventBus.getDefault().post(RepositoryFragment.MESSAGE);
        super.onBackPressed();
    }

    @Override
    public void renameSuccess(String name) {
        renameDialog.dismiss();
        ToastUtilsKt.toast(this, "修改成功");
        setTitle(name);
        EventBus.getDefault().post(RepositoryFragment.MESSAGE);
    }

    @Override
    public void onDialogClick(View view) {
        switch (view.getId()) {
            case R.id.tvDelete:
                mPresenter.deleteLib(listId);
                break;
            case R.id.tvRename:
                mPresenter.rename(listId, (String) view.getTag());
                break;
        }
    }
}
