package com.tuzhi.application.moudle.taskdetails;


import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.View;

import com.tuzhi.application.R;
import com.tuzhi.application.bean.EventBusBean;
import com.tuzhi.application.bean.ItemBean;
import com.tuzhi.application.bean.Person;
import com.tuzhi.application.databinding.ActivityTaskDetailsBinding;
import com.tuzhi.application.dialog.InputDialog;
import com.tuzhi.application.inter.ItemClickListener;
import com.tuzhi.application.inter.OnDialogClickListener;
import com.tuzhi.application.item.GeneralLoadFootViewItem;
import com.tuzhi.application.moudle.basemvp.MVPBaseActivity;
import com.tuzhi.application.moudle.createtask.NewTaskBean;
import com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.choosecolleague.bean.ChooseColleagueItemBean;
import com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.choosecolleague.mvp.ChooseColleagueActivity;
import com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.mvp.KnowledgeDetailsActivity;
import com.tuzhi.application.utils.ActivitySkipUtilsKt;
import com.tuzhi.application.utils.KeyBoardUtils;
import com.tuzhi.application.utils.ToastUtilsKt;
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
 *
 * @author wangpeng
 */

public class TaskDetailsActivity extends MVPBaseActivity<TaskDetailsContract.View, TaskDetailsPresenter> implements TaskDetailsContract.View, ItemClickListener, LoadMoreListener, SwipeRefreshLayout.OnRefreshListener, OnDialogClickListener {
    public static final String ID = "ID";
    public static final String EVENT_NAME = "TaskDetailsActivity";
    public static final int EVENT_TYPE_MANAGER = 0;
    public static final int EVENT_TYPE_PEOPLE = 1;
    public static final int EVENT_TYPE_CARD = 2;
    public static final int EVENT_TYPE_INFORM = 3;
    private ArrayList<ItemBean> data = new ArrayList<>();
    private ArrayList<String> informPeopleIds = new ArrayList<>();
    private ActivityTaskDetailsBinding binding;
    private NewTaskBean bean;
    private String id;
    private int type;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_task_details;
    }

    @Override
    protected void init(ViewDataBinding viewDataBinding) {
        EventBus.getDefault().register(this);
        id = getIntent().getStringExtra(ID);
        binding = (ActivityTaskDetailsBinding) viewDataBinding;
        binding.tvRelevance.setText("@");
        setRelevanceColor();
        binding.setActivity(this);
        binding.setComment("");
        binding.rrv.isShowRefreshView(true);
        binding.rrv.setLoadListener(this);
        binding.rrv.setOnRefreshListener(this);
        binding.rrv.isEnabled(false);
        CommonRcvAdapter<ItemBean> adapter = new CommonRcvAdapter<ItemBean>(data) {
            @NonNull
            @Override
            public AdapterItem createItem(Object o) {
                String itemType = (String) o;
                switch (itemType) {
                    case TaskDetailsCommentItem.TYPE:
                        return new TaskDetailsCommentItem();
                    case TaskDetailsItem.TYPE:
                        TaskDetailsItem item = new TaskDetailsItem();
                        item.setClickListener(TaskDetailsActivity.this);
                        return item;
                    default:
                        return new GeneralLoadFootViewItem();
                }
            }

            @Override
            public Object getItemType(ItemBean listItemBean) {
                return listItemBean.getItemType();
            }
        };
        binding.rrv.setAdapter(adapter);
    }

    public void back() {
        onBackPressed();
    }


    private void setRelevanceColor() {
        if (informPeopleIds.size() > 0) {
            binding.tvRelevance.setTextColor(getResources().getColor(R.color.colorBlue71b7fd));
        } else {
            binding.tvRelevance.setTextColor(getResources().getColor(R.color.colorTextHint));
        }
    }

    @Override
    public void onItemClick(View view) {
        switch (view.getId()) {
            case R.id.cb:
                bean.setChecked(!bean.isChecked());
                if (bean.isChecked()) {
                    mPresenter.taskFinished(bean.getTaskId());
                } else {
                    mPresenter.taskUnfinished(bean.getTaskId());
                }
                break;
            case R.id.tvTitle:
                showInputDialog("任务标题", bean.getTaskTitle(), 0);
                break;
            case R.id.tvSummery:
                showInputDialog("任务描述", bean.getTaskSummary(), 1);
                break;
            case R.id.flChooseManager:
                chooseManager();
                break;
            case R.id.flChoosePeople:
                choosePeople();
                break;
            case R.id.tvAddCard:
                chooseCard();
                break;
            case R.id.flTaskCard:
                shipKnowledgeCardActivity(view);
                break;
            case R.id.tvRemove:
                bean.getData().remove((int) view.getTag());
                bean.getAdapter().notifyDataSetChanged();
                mPresenter.updateTask(bean);
            default:
                break;
        }
    }

    private void shipKnowledgeCardActivity(View view) {
        ItemBean itemBean = (ItemBean) view.getTag();
        ActivitySkipUtilsKt.toActivity(this, KnowledgeDetailsActivity.class, KnowledgeDetailsActivity.ID, itemBean.getId());
    }


    private void showInputDialog(String title, String text, int type) {
        this.type = type;
        InputDialog dialog = new InputDialog(this);
        dialog.setTitle(title);
        dialog.setText(text);
        dialog.setClickListener(this);
        dialog.show();
    }


    public void chooseManager() {
        Intent intent = new Intent(this, ChooseColleagueActivity.class);
        intent.putExtra(ChooseColleagueActivity.TYPE, ChooseColleagueActivity.TYPE_TASK_MANAGER);
        intent.putExtra(ChooseColleagueActivity.EVENT_NAME, EVENT_NAME);
        intent.putExtra(ChooseColleagueActivity.LIB_ID, bean.getTaskLibId());
        intent.putExtra(ChooseColleagueActivity.TASK_ID, id);
        startActivity(intent);
    }

    public void choosePeople() {
        Intent intent = new Intent(this, ChooseColleagueActivity.class);
        ArrayList<Person> taskPeople = bean.getTaskPeople();
        ArrayList<String> peopleId = new ArrayList<>();
        if (taskPeople != null) {
            for (Person taskPerson : taskPeople) {
                peopleId.add(taskPerson.getId());
            }
        }
        intent.putStringArrayListExtra(ChooseColleagueActivity.TASK_PEOPLE_ID, peopleId);
        intent.putExtra(ChooseColleagueActivity.TYPE, ChooseColleagueActivity.TYPE_TASK_PEOPLE);
        intent.putExtra(ChooseColleagueActivity.EVENT_NAME, EVENT_NAME);
        intent.putExtra(ChooseColleagueActivity.EVENT_TYPE, EVENT_TYPE_PEOPLE);
        intent.putExtra(ChooseColleagueActivity.LIB_ID, bean.getTaskLibId());
        intent.putExtra(ChooseColleagueActivity.TASK_ID, id);
        startActivity(intent);
    }

    public void informPeople() {
        Intent intent = new Intent(this, ChooseColleagueActivity.class);
        intent.putStringArrayListExtra(ChooseColleagueActivity.TASK_PEOPLE_ID, informPeopleIds);
        intent.putExtra(ChooseColleagueActivity.TYPE, ChooseColleagueActivity.TYPE_TASK_PEOPLE);
        intent.putExtra(ChooseColleagueActivity.EVENT_NAME, EVENT_NAME);
        intent.putExtra(ChooseColleagueActivity.EVENT_TYPE, EVENT_TYPE_INFORM);
        intent.putExtra(ChooseColleagueActivity.LIB_ID, bean.getTaskLibId());
        intent.putExtra(ChooseColleagueActivity.TASK_ID, id);
        startActivity(intent);
    }

    public void chooseCard() {
        if (!TextUtils.isEmpty(bean.getTaskLibId())) {
            Intent intent = new Intent(this, ChooseColleagueActivity.class);
            intent.putExtra(ChooseColleagueActivity.TYPE, ChooseColleagueActivity.TYPE_CHOOSE_CHANNEL);
            intent.putExtra(ChooseColleagueActivity.LIB_ID, bean.getTaskLibId());
            intent.putExtra(ChooseColleagueActivity.EVENT_NAME, EVENT_NAME);
            startActivity(intent);
        } else {
            ToastUtilsKt.toast(this, "请先选择知识库");
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventBus(EventBusBean busBean) {
        if (busBean.getName().equals(EVENT_NAME)) {
            if (busBean.getEventType() == EVENT_TYPE_MANAGER) {
                dealTaskManager(busBean);
            } else if (busBean.getEventType() == EVENT_TYPE_PEOPLE) {
                dealTaskPeople(busBean);
            } else if (busBean.getEventType() == EVENT_TYPE_CARD) {
                dealTaskCard(busBean);
            } else if (busBean.getEventType() == EVENT_TYPE_INFORM) {
                dealTaskInform(busBean);
            }
            mPresenter.updateTask(bean);
        }
    }

    public void commitComment(String comment) {
        if (TextUtils.isEmpty(comment)) {
            ToastUtilsKt.toast(this, "请填写内容");
            return;
        }
        mPresenter.commitComment(id, comment, getInformPeople());
    }


    private String getInformPeople() {
        StringBuilder ids = new StringBuilder();
        for (String informPeopleId : informPeopleIds) {
            ids.append(",").append(informPeopleId);
        }
        if (ids.length() > 0) {
            ids.deleteCharAt(0);
        }
        return ids.toString();
    }

    private void dealTaskInform(EventBusBean busBean) {
        informPeopleIds.clear();
        ArrayList<ChooseColleagueItemBean> chooseData = (ArrayList<ChooseColleagueItemBean>) busBean.getObject();
        for (ChooseColleagueItemBean chooseDatum : chooseData) {
            informPeopleIds.add(chooseDatum.getId());
        }
        setRelevanceColor();
    }

    private void dealTaskCard(EventBusBean busBean) {
        ItemBean itemBean = (ItemBean) busBean.getObject();
        bean.addData(itemBean);
        bean.getAdapter().notifyDataSetChanged();
    }

    private void dealTaskPeople(EventBusBean busBean) {
        ArrayList<ChooseColleagueItemBean> chooseData = (ArrayList<ChooseColleagueItemBean>) busBean.getObject();
        ArrayList<Person> persons = new ArrayList<>();
        for (ChooseColleagueItemBean chooseDatum : chooseData) {
            Person person = new Person();
            person.setId(chooseDatum.getId());
            person.setImage(chooseDatum.getPortrait());
            person.setName(chooseDatum.getNickName());
            persons.add(person);
        }
        bean.setTaskPeople(persons);
    }

    private void dealTaskManager(EventBusBean busBean) {
        ChooseColleagueItemBean colleagueItemBean = (ChooseColleagueItemBean) busBean.getObject();
        Person person = new Person();
        person.setId(colleagueItemBean.getId());
        person.setImage(colleagueItemBean.getPortrait());
        person.setName(colleagueItemBean.getNickName());
        bean.setTaskManager(person);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void loadMoreListener(int page) {
        mPresenter.downloadData(id, page);
    }

    @Override
    public void onRefresh() {
        mPresenter.downloadData(id, 0);
    }

    @Override
    public void downloadSuccess(ArrayList<ItemBean> httpData, boolean haveNextPage, int page) {
        if (page == 0) {
            ItemBean itemBean = httpData.get(0);
            bean = itemBean.getTaskBean();
        }
        binding.rrv.downLoadFinish(page, haveNextPage, data, httpData, false);
    }

    @Override
    public void commitCommentSuccess() {
        ToastUtilsKt.toast(this, "评论成功");
        KeyBoardUtils.hindKeyBoard(binding.et);
        mPresenter.downloadData(id, 0);
    }

    @Override
    public void onDialogClick(View view) {
        String text = (String) view.getTag();
        if (type == 0) {
            bean.setTaskTitle(text);
        } else {
            bean.setTaskSummary(text);
        }
        mPresenter.updateTask(bean);
    }
}
