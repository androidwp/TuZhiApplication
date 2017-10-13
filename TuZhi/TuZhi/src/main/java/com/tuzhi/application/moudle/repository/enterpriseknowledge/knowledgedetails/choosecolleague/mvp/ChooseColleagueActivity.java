package com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.choosecolleague.mvp;


import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.EditText;

import com.tuzhi.application.R;
import com.tuzhi.application.databinding.ActivityChooseColleagueBinding;
import com.tuzhi.application.dialog.SendCardDialog;
import com.tuzhi.application.inter.ItemClickListener;
import com.tuzhi.application.inter.OnDialogClickListener;
import com.tuzhi.application.item.GeneralLoadFootViewItem;
import com.tuzhi.application.moudle.basemvp.MVPBaseActivity;
import com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.choosecolleague.bean.ChooseColleagueItemBean;
import com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.choosecolleague.item.ChooseColleagueItem;
import com.tuzhi.application.utils.KeyBoardUtils;
import com.tuzhi.application.view.LoadMoreListener;

import java.util.ArrayList;

import kale.adapter.CommonRcvAdapter;
import kale.adapter.item.AdapterItem;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class ChooseColleagueActivity extends MVPBaseActivity<ChooseColleagueContract.View, ChooseColleaguePresenter> implements ChooseColleagueContract.View, SwipeRefreshLayout.OnRefreshListener, LoadMoreListener, ItemClickListener, OnDialogClickListener {
    //卡片的id
    public static final String CID = "CID";
    //卡片的标题
    public static final String CTITLE = "CTITLE";

    private ArrayList<ChooseColleagueItemBean> data = new ArrayList<>();
    private ActivityChooseColleagueBinding binding;
    private ArrayList<ChooseColleagueItemBean> chooseData = new ArrayList<>();
    private String cId;
    private String cTitle;
    private SendCardDialog dialog;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_choose_colleague;
    }

    @Override
    protected void init(ViewDataBinding viewDataBinding) {
        cId = getIntent().getStringExtra(CID);
        cTitle = getIntent().getStringExtra(CTITLE);
        binding = (ActivityChooseColleagueBinding) viewDataBinding;
        binding.setActivity(this);
        binding.rrv.isShowRefreshView(true);
        binding.rrv.setOnRefreshListener(this);
        binding.rrv.setLoadListener(this);
        CommonRcvAdapter<ChooseColleagueItemBean> adapter = new CommonRcvAdapter<ChooseColleagueItemBean>(data) {
            @NonNull
            @Override
            public AdapterItem createItem(Object o) {
                String itemType = (String) o;
                switch (itemType) {
                    case GeneralLoadFootViewItem.TYPE:
                        return new GeneralLoadFootViewItem();
                    default:
                        ChooseColleagueItem chooseColleagueItem = new ChooseColleagueItem();
                        chooseColleagueItem.setClickListener(ChooseColleagueActivity.this);
                        return chooseColleagueItem;
                }
            }

            @Override
            public Object getItemType(ChooseColleagueItemBean colleagueItemBean) {
                return colleagueItemBean.getItemType();
            }
        };

        binding.rrv.setAdapter(adapter);
    }

    @Override
    public void onRefresh() {
        chooseData.clear();
        binding.setPeople("");
        mPresenter.downloadData(cId, 0);
    }

    @Override
    public void loadMoreListener(int page) {
        mPresenter.downloadData(cId, page);
    }

    @Override
    public void downloadFinish(ArrayList<ChooseColleagueItemBean> newData, boolean haveNextPage, int page) {
        binding.rrv.downLoadFinish(page, haveNextPage, data, newData, false);
    }

    @Override
    public void downloadFinish() {
        binding.rrv.isShowRefreshView(false);
    }

    @Override
    public void shareCardSuccess() {
        dialog.dismiss();
        onBackPressed();
    }

    public void back() {
        onBackPressed();
    }

    public void sure() {
        if (chooseData.size() > 0) {
            dialog = new SendCardDialog(this);
            dialog.setView(new EditText(this));
            dialog.setClickListener(this);
            dialog.setTitle(cTitle);
            dialog.setArrayList(chooseData);
            dialog.show();
            KeyBoardUtils.showKeyBoard(this);
        }
    }

    @Override
    public void onItemClick(View view) {
        ChooseColleagueItemBean chooseColleagueItemBean = (ChooseColleagueItemBean) view.getTag();
        chooseColleagueItemBean.setChooseStatus(!chooseColleagueItemBean.isChooseStatus());
        if (chooseColleagueItemBean.isChooseStatus()) {
            chooseData.add(chooseColleagueItemBean);
        } else {
            chooseData.remove(chooseColleagueItemBean);
        }
        if (chooseData.size() == 0) {
            binding.setPeople("");
        } else {
            binding.setPeople("确认(" + chooseData.size() + ")");
        }
    }

    @Override
    public void onDialogClick(View view) {
        String content = (String) view.getTag();
        StringBuilder staffIds = new StringBuilder();
        for (int i = 0; i < chooseData.size(); i++) {
            staffIds.append(chooseData.get(i).getId());
            if (i != chooseData.size() - 1) {
                staffIds.append(",");
            }
        }
        mPresenter.shareCard(cId, staffIds.toString(), content);
    }
}
