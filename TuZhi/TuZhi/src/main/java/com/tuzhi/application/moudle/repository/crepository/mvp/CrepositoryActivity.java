package com.tuzhi.application.moudle.repository.crepository.mvp;


import android.databinding.ViewDataBinding;

import com.tuzhi.application.R;
import com.tuzhi.application.bean.EventBusBean;
import com.tuzhi.application.bean.ItemBean;
import com.tuzhi.application.databinding.ActivityCrepositoryBinding;
import com.tuzhi.application.moudle.basemvp.MVPBaseActivity;
import com.tuzhi.application.moudle.createtask.CreateTaskActivity;
import com.tuzhi.application.moudle.createtask.TaskCardItem;
import com.tuzhi.application.moudle.repository.crepository.bean.CreateRepositoryBean;
import com.tuzhi.application.moudle.repository.crepository.bean.HttpCreateCardBean;
import com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.choosecolleague.mvp.ChooseColleagueActivity;
import com.tuzhi.application.moudle.repository.knowledgachannel.mvp.KnowledgeChannelActivity;
import com.tuzhi.application.moudle.taskdetails.TaskDetailsActivity;
import com.tuzhi.application.utils.ConstantKt;
import com.tuzhi.application.utils.KeyBoardUtils;

import org.greenrobot.eventbus.EventBus;


/**
 * 创建知识库页面
 *
 * @author wangpeng
 */

public class CrepositoryActivity extends MVPBaseActivity<CrepositoryContract.View, CrepositoryPresenter> implements CrepositoryContract.View {
    public static final String ID = "ID";
    private String id;
    private boolean canClick = true;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_crepository;
    }

    @Override
    protected void init(ViewDataBinding viewDataBinding) {
        ActivityCrepositoryBinding binding = (ActivityCrepositoryBinding) viewDataBinding;
        binding.setActivity(this);
        id = getIntent().getStringExtra(ID);
        binding.setData(new CreateRepositoryBean("新建卡片", "请输入卡片名称", ""));
        KeyBoardUtils.showKeyBoard(this);
    }

    public void cancel() {
        onBackPressed();
    }

    public void commit(String name) {
        if (canClick) {
            canClick = false;
            mPresenter.commit(name, id);
        }
    }


    @Override
    public void commitFinish(HttpCreateCardBean bean) {
        EventBus.getDefault().post(KnowledgeChannelActivity.MESSAGE);
        setResult(ConstantKt.getCREATE_CODE());
        sendCard(bean);
        onBackPressed();
    }

    /**
     * 任务中  添加空白卡片是用到此方法。传出去选中的卡片信息谁用谁拿
     *
     * @param bean
     */
    private void sendCard(HttpCreateCardBean bean) {
        HttpCreateCardBean.ArticleMapBean articleMap = bean.getArticleMap();
        ItemBean itemBean = new ItemBean(TaskCardItem.TYPE);
        itemBean.setId(articleMap.getId());
        itemBean.setTitle(articleMap.getTitle());
        itemBean.setTime(articleMap.getUpdateTime());

        EventBusBean taskBean = new EventBusBean();
        taskBean.setName(CreateTaskActivity.EVENT_NAME);
        taskBean.setEventType(CreateTaskActivity.EVENT_TYPE_CARD);
        taskBean.setObject(itemBean);
        EventBus.getDefault().post(taskBean);

        EventBusBean taskDetailBean = new EventBusBean();
        taskDetailBean.setName(TaskDetailsActivity.EVENT_NAME);
        taskDetailBean.setEventType(TaskDetailsActivity.EVENT_TYPE_CARD);
        taskDetailBean.setObject(itemBean);
        EventBus.getDefault().post(taskDetailBean);
        EventBus.getDefault().post(ChooseColleagueActivity.FINISH);
    }


    @Override
    public void commitError() {
        canClick = true;
    }
}
