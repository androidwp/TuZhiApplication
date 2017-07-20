package com.tuzhi.application.moudle.enterpriseknowledge.mvp;


import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.widget.EditText;

import com.tuzhi.application.R;
import com.tuzhi.application.databinding.ActivityEnterpriseKnowledgeBinding;
import com.tuzhi.application.dialog.DeleteDialog;
import com.tuzhi.application.dialog.RenameDialog;
import com.tuzhi.application.item.GeneralEmptyFootViewItem;
import com.tuzhi.application.item.GeneralLoadFootViewItem;
import com.tuzhi.application.moudle.basemvp.MVPBaseActivity;
import com.tuzhi.application.moudle.crepository.mvp.CrepositoryActivity;
import com.tuzhi.application.moudle.enterpriseknowledge.bean.EnterpriseKnowledgeListItemBean;
import com.tuzhi.application.moudle.enterpriseknowledge.item.EnterpriseKnowledgeListItem;
import com.tuzhi.application.utils.ConstantKt;
import com.tuzhi.application.utils.KeyBoardUtils;
import com.tuzhi.application.utils.SharedPreferencesUtilsKt;
import com.tuzhi.application.view.ActionSheet;
import com.tuzhi.application.view.LoadMoreListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import kale.adapter.CommonRcvAdapter;
import kale.adapter.item.AdapterItem;

public class EnterpriseKnowledgeActivity extends MVPBaseActivity<EnterpriseKnowledgeContract.View, EnterpriseKnowledgePresenter> implements EnterpriseKnowledgeContract.View, SwipeRefreshLayout.OnRefreshListener, LoadMoreListener, ActionSheet.ActionSheetListener {
    public static final String MESSAGE = "EnterpriseKnowledgeActivity_refresh";
    public static final String ID = "id";
    public static final String TITLE = "TITLE";
    private String listId;
    private ArrayList<EnterpriseKnowledgeListItemBean> mData = new ArrayList<>();
    private ActivityEnterpriseKnowledgeBinding binding;
    private ActionSheet actionSheet;
    private String title;
    private String flagDeleteLib;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_enterprise_knowledge;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(String event) {
        if (TextUtils.equals(event, MESSAGE)){
            onRefresh();
        }
    }

    @Override
    protected void init(ViewDataBinding viewDataBinding) {
        EventBus.getDefault().register(this);
        flagDeleteLib = SharedPreferencesUtilsKt.getLongCache(this, ConstantKt.getFLAG_DELETE_LIB());
        binding = (ActivityEnterpriseKnowledgeBinding) viewDataBinding;
        listId = getIntent().getStringExtra(ID);
        title = getIntent().getStringExtra(TITLE);
        setTitle(title);
        binding.setActivity(this);
        binding.rrv.isShowRefreshView(true);
        binding.rrv.setOnRefreshListener(this);
        binding.rrv.setLoadListener(this);
        binding.rrv.setTitle("该知识库下还没有频道哦");
        binding.rrv.setInfo("点击上方的\"+\"号，创建频道");
        CommonRcvAdapter<EnterpriseKnowledgeListItemBean> adapter = new CommonRcvAdapter<EnterpriseKnowledgeListItemBean>(mData) {
            @NonNull
            @Override
            public AdapterItem createItem(Object o) {
                String itemType = (String) o;
                switch (itemType) {
                    case GeneralLoadFootViewItem.TYPE:
                        return new GeneralLoadFootViewItem();
                    case GeneralEmptyFootViewItem.TYPE:
                        return new GeneralEmptyFootViewItem();
                    default:
                        return new EnterpriseKnowledgeListItem();
                }
            }

            @Override
            public Object getItemType(EnterpriseKnowledgeListItemBean enterpriseKnowledgeListItemBean) {
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
        intent.putExtra(CrepositoryActivity.TYPE, CrepositoryActivity.MOUDLE);
        intent.putExtra(CrepositoryActivity.LibId, listId);
        startActivityForResult(intent, ConstantKt.getCREATE_CODE());
    }

    public void openMenu() {
        if (TextUtils.equals(flagDeleteLib, ConstantKt.getValue_true())) {
            actionSheet = ActionSheet.createBuilder(this, getSupportFragmentManager())
                    .setCancelButtonTitle("取消")
                    .setOtherButtonTitles("重命名知识库", "删除知识库")
                    .setCancelableOnTouchOutside(true)
                    .setListener(this).show();
        } else {
            actionSheet = ActionSheet.createBuilder(this, getSupportFragmentManager())
                    .setCancelButtonTitle("取消")
                    .setOtherButtonTitles("重命名知识库")
                    .setCancelableOnTouchOutside(true)
                    .setListener(this).show();
        }
    }

    @Override
    public void onRefresh() {
        mPresenter.downLoadData(listId, 0);
    }

    @Override
    public void downloadFinish(int page, boolean haveNextPage, ArrayList<EnterpriseKnowledgeListItemBean> data) {
        binding.rrv.downLoadFinish(page, haveNextPage, mData, data, true);
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
            RenameDialog renameDialog = new RenameDialog(this, R.style.dialog);
            renameDialog.setView(new EditText(this));
            renameDialog.setText(title);
            renameDialog.setLibId(listId);
            renameDialog.setType(RenameDialog.LIB);
            renameDialog.show();
            KeyBoardUtils.showKeyBoard(this);
        } else {
            DeleteDialog deleteDialog = new DeleteDialog(this, R.style.dialog);
            deleteDialog.setLibId(listId);
            deleteDialog.setType(RenameDialog.LIB);
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
}
