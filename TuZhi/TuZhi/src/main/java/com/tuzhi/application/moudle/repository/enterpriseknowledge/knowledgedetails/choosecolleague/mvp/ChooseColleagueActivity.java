package com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.choosecolleague.mvp;


import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.tuzhi.application.R;
import com.tuzhi.application.bean.EventBusBean;
import com.tuzhi.application.bean.ItemBean;
import com.tuzhi.application.databinding.ActivityChooseColleagueBinding;
import com.tuzhi.application.dialog.SendCardDialog;
import com.tuzhi.application.inter.ItemClickListener;
import com.tuzhi.application.inter.OnDialogClickListener;
import com.tuzhi.application.item.GeneralLoadFootViewItem;
import com.tuzhi.application.moudle.basemvp.MVPBaseActivity;
import com.tuzhi.application.moudle.createtask.CreateTaskActivity;
import com.tuzhi.application.moudle.createtask.TaskCardItem;
import com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.choosecolleague.bean.ChooseColleagueItemBean;
import com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.choosecolleague.item.ChooseCardItem;
import com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.choosecolleague.item.ChooseChannelItem;
import com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.choosecolleague.item.ChooseColleagueItem;
import com.tuzhi.application.utils.KeyBoardUtils;
import com.tuzhi.application.view.LoadMoreListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import kale.adapter.CommonRcvAdapter;
import kale.adapter.item.AdapterItem;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class ChooseColleagueActivity extends MVPBaseActivity<ChooseColleagueContract.View, ChooseColleaguePresenter> implements ChooseColleagueContract.View, SwipeRefreshLayout.OnRefreshListener, LoadMoreListener, ItemClickListener, OnDialogClickListener {

    public static final String FINISH = "ChooseColleagueActivity_FINISH";

    //多处使用该界面进行复用
    public static final String TYPE = "TYPE";
    //卡片详情入口 分享使用
    public static final String TYPE_CARD_DETAIL = "TYPE_CARD_DETAIL";
    //任务选择管理者使用
    public static final String TYPE_TASK_MANAGER = "TYPE_TASK_MANAGER";
    //任务选择成员使用
    public static final String TYPE_TASK_PEOPLE = "TYPE_TASK_PEOPLE";
    //选择频道
    public static final String TYPE_CHOOSE_CHANNEL = "TYPE_CHOOSE_CHANNEL";
    //选择卡片
    public static final String TYPE_CHOOSE_CARD = "TYPE_CHOOSE_CARD";
    //频道加载需要lib_id
    public static final String LIB_ID = "LIB_ID";
    //卡片加载需要channel_id
    public static final String CHANNEL_ID = "CHANNEL_ID";
    //卡片加载需要的头名称
    public static final String CHANNEL_NAME = "CHANNEL_NAME";
    //已经选择的人id
    public static final String TASK_PEOPLE_ID = "TASK_PEOPLE_ID";
    //卡片的id
    public static final String CID = "CID";
    //卡片的标题
    public static final String CTITLE = "CTITLE";

    private ArrayList<ChooseColleagueItemBean> data = new ArrayList<>();
    private ArrayList<ChooseColleagueItemBean> dataOriginal = new ArrayList<>();
    private ActivityChooseColleagueBinding binding;
    private ArrayList<ChooseColleagueItemBean> chooseData = new ArrayList<>();
    private String cId;
    private String cTitle;
    private SendCardDialog dialog;
    private String type;
    private ArrayList<String> peopleId;
    private String libID;
    private String channelId;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_choose_colleague;
    }

    @Override
    protected void init(ViewDataBinding viewDataBinding) {
        EventBus.getDefault().register(this);
        type = getIntent().getStringExtra(TYPE);
        binding = (ActivityChooseColleagueBinding) viewDataBinding;
        binding.setType(type);
        switch (type) {
            case TYPE_CARD_DETAIL:
                cId = getIntent().getStringExtra(CID);
                cTitle = getIntent().getStringExtra(CTITLE);
                binding.setTitle("发送给");
                binding.setLittleTitle("成员");
                break;
            case TYPE_TASK_PEOPLE:
                binding.setTitle("选择参与者");
                binding.setLittleTitle("成员");
                peopleId = getIntent().getStringArrayListExtra(TASK_PEOPLE_ID);
                cId = "1";
                break;
            case TYPE_TASK_MANAGER:
                binding.setTitle("选择负责人");
                binding.setLittleTitle("成员");
                cId = "1";
                break;
            case TYPE_CHOOSE_CHANNEL:
                binding.setTitle("添加关联知识");
                libID = getIntent().getStringExtra(LIB_ID);
                break;
            case TYPE_CHOOSE_CARD:
                binding.setTitle("添加关联知识");
                binding.setLittleTitle(getIntent().getStringExtra(CHANNEL_NAME));
                channelId = getIntent().getStringExtra(CHANNEL_ID);
                break;
        }
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
                    case ChooseCardItem.TYPE:
                        ChooseCardItem cardItem = new ChooseCardItem();
                        cardItem.setClickListener(ChooseColleagueActivity.this);
                        return cardItem;
                    case ChooseChannelItem.TYPE:
                        ChooseChannelItem chooseChannelItem = new ChooseChannelItem();
                        chooseChannelItem.setClickListener(ChooseColleagueActivity.this);
                        return chooseChannelItem;
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
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMainEvent(String msg) {
        if (msg.equals(FINISH)) {
            finish();
        }
    }

    @Override
    public void onRefresh() {
        chooseData.clear();
        binding.setChooseNumber("");
        switch (type) {
            case TYPE_CHOOSE_CHANNEL:
                mPresenter.downloadDataChannel(libID, 0);
                break;
            case TYPE_CHOOSE_CARD:
                mPresenter.downloadDataCare(channelId, 0);
                break;
            default:
                mPresenter.downloadDataColleague(cId, 0);
                break;
        }
    }

    @Override
    public void loadMoreListener(int page) {
        switch (type) {
            case TYPE_CHOOSE_CHANNEL:
                mPresenter.downloadDataChannel(libID, page);
                break;
            case TYPE_CHOOSE_CARD:
                mPresenter.downloadDataCare(channelId, page);
                break;
            default:
                mPresenter.downloadDataColleague(cId, page);
                break;
        }
    }

    @Override
    public void downloadFinish(ArrayList<ChooseColleagueItemBean> newData, boolean haveNextPage, int page) {
        dataOriginal.clear();
        dataOriginal.addAll(newData);
        if (type.equals(TYPE_TASK_PEOPLE)) {
            saveChooseStatue();
        }
        binding.rrv.downLoadFinish(page, haveNextPage, data, newData, false);
    }

    //选择成员的时候，保存选中的状态
    private void saveChooseStatue() {
        for (ChooseColleagueItemBean chooseColleagueItemBean : dataOriginal) {
            for (String id : peopleId) {
                if (chooseColleagueItemBean.getId().equals(id)) {
                    chooseColleagueItemBean.setChooseStatus(true);
                    chooseData.add(chooseColleagueItemBean);
                }
            }
        }
        if (chooseData.size() == 0) {
            binding.setChooseNumber("");
        } else {
            binding.setChooseNumber("确认(" + chooseData.size() + ")");
        }
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
        if (type.equals(TYPE_CARD_DETAIL)) {
            if (chooseData.size() > 0) {
                dialog = new SendCardDialog(this);
                dialog.setView(new EditText(this));
                dialog.setClickListener(this);
                dialog.setTitle(cTitle);
                dialog.setArrayList(chooseData);
                dialog.show();
                KeyBoardUtils.showKeyBoard(this);
            }
        } else {
            sendPeople(chooseData);
            back();
        }

    }

    @Override
    public void onItemClick(View view) {
        ChooseColleagueItemBean chooseColleagueItemBean = (ChooseColleagueItemBean) view.getTag();
        if (view.getId() == R.id.flChooseColleague) {
            if (type.equals(TYPE_TASK_MANAGER)) {
                sendManager(chooseColleagueItemBean);
                back();
            } else {
                chooseColleagueItemBean.setChooseStatus(!chooseColleagueItemBean.isChooseStatus());
                if (chooseColleagueItemBean.isChooseStatus()) {
                    chooseData.add(chooseColleagueItemBean);
                } else {
                    chooseData.remove(chooseColleagueItemBean);
                }
                if (chooseData.size() == 0) {
                    binding.setChooseNumber("");
                } else {
                    binding.setChooseNumber("确认(" + chooseData.size() + ")");
                }
            }
        } else if (view.getId() == R.id.flChooseChannel) {
            Intent intent = new Intent(this, ChooseColleagueActivity.class);
            intent.putExtra(TYPE, TYPE_CHOOSE_CARD);
            intent.putExtra(CHANNEL_ID, chooseColleagueItemBean.getKcid());
            intent.putExtra(CHANNEL_NAME, chooseColleagueItemBean.getTitle());
            startActivity(intent);
        } else if (view.getId() == R.id.llChooseCard) {
            sendCard(chooseColleagueItemBean);
            EventBus.getDefault().post(ChooseColleagueActivity.FINISH);
        }
    }

    private void sendCard(ChooseColleagueItemBean chooseColleagueItemBean) {
        ItemBean itemBean = new ItemBean(TaskCardItem.TYPE);
        itemBean.setId(chooseColleagueItemBean.getId());
        itemBean.setTitle(chooseColleagueItemBean.getTitle());
        itemBean.setPosition("产品部 > 需求文档");
        EventBusBean busBean = new EventBusBean();
        busBean.setName(CreateTaskActivity.EVENT_NAME);
        busBean.setEventType(CreateTaskActivity.EVENT_TYPE_CARD);
        busBean.setObject(itemBean);
        EventBus.getDefault().post(busBean);
    }


    private void sendManager(ChooseColleagueItemBean chooseColleagueItemBean) {
        EventBusBean busBean = new EventBusBean();
        busBean.setName(CreateTaskActivity.EVENT_NAME);
        busBean.setEventType(CreateTaskActivity.EVENT_TYPE_MANAGER);
        busBean.setObject(chooseColleagueItemBean);
        EventBus.getDefault().post(busBean);
    }

    private void sendPeople(ArrayList<ChooseColleagueItemBean> chooseData) {
        EventBusBean busBean = new EventBusBean();
        busBean.setName(CreateTaskActivity.EVENT_NAME);
        busBean.setEventType(CreateTaskActivity.EVENT_TYPE_PEOPLE);
        busBean.setObject(chooseData);
        EventBus.getDefault().post(busBean);
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

    public void onTextChange(CharSequence text) {
        data.clear();
        if (TextUtils.isEmpty(text)) {
            data.addAll(dataOriginal);
        } else {
            for (ChooseColleagueItemBean chooseColleagueItemBean : dataOriginal) {
                if (chooseColleagueItemBean.getNickName().contains(text)) {
                    data.add(chooseColleagueItemBean);
                }
            }
        }
        binding.rrv.notifyDataSetChanged();
    }
}
