package com.tuzhi.application.moudle.message.read.item;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;

import com.tuzhi.application.R;
import com.tuzhi.application.databinding.ItemReadUnreadBinding;
import com.tuzhi.application.item.BaseItem;
import com.tuzhi.application.moudle.message.read.bean.ReadListItemBean;
import com.tuzhi.application.moudle.message.read.mvp.ReadFragment;
import com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.commentlist.mvp.CommentListActivity;
import com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.mvp.KnowledgeDetailsActivity;
import com.tuzhi.application.moudle.taskdetails.TaskDetailsActivity;
import com.tuzhi.application.utils.HttpCallBack;
import com.tuzhi.application.utils.HttpUtilsKt;
import com.tuzhi.application.utils.ToastUtilsKt;

import org.greenrobot.eventbus.EventBus;

import java.util.WeakHashMap;

/**
 * Created by wangpeng on 2017/8/1.
 */

public class ReadListItem extends BaseItem<ReadListItemBean> {
    public static final String TYPE = "ReadListItem";
    private static final String URL = "user/notice";

    private ItemReadUnreadBinding binding;

    @Override
    public void bindView(View view) {
        binding = DataBindingUtil.bind(view);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_read_unread;
    }

    @Override
    public void handleData(ReadListItemBean readListItemBean, int i) {
        binding.setItem(this);
        binding.setData(readListItemBean);
        binding.executePendingBindings();
    }

    public void skip(ReadListItemBean readListItemBean) {
        if (readListItemBean.isLimit()) {
            Intent intent;
            switch (readListItemBean.getContentType()) {
                case 3:
                    if (TextUtils.equals(readListItemBean.getCommentId(), "0")) {
                        intent = toKnowledgeDetails(readListItemBean);
                    } else {
                        intent = toCommentList(readListItemBean);
                    }
                    break;
                case 2:
                    //等于2的时候  去任务详情
                    if (TextUtils.equals(readListItemBean.getObjectType(), "2")) {
                        intent = toTask(readListItemBean);
                    } else {
                        intent = toCommentList(readListItemBean);
                    }
                    break;
                default:
                    intent = toKnowledgeDetails(readListItemBean);
                    break;
            }
            context.startActivity(intent);
            readMessage(readListItemBean.getId());
        } else {
            ToastUtilsKt.toast(context, "无查看权限");
        }
    }

    @NonNull
    private Intent toKnowledgeDetails(ReadListItemBean readListItemBean) {
        Intent intent;
        intent = new Intent(context, KnowledgeDetailsActivity.class);
        intent.putExtra(KnowledgeDetailsActivity.ID, readListItemBean.getArticleId());
        return intent;
    }

    @NonNull
    private Intent toCommentList(ReadListItemBean readListItemBean) {
        Intent intent;
        intent = new Intent(context, CommentListActivity.class);
        intent.putExtra(CommentListActivity.AID, readListItemBean.getArticleId());
        intent.putExtra(CommentListActivity.CID, readListItemBean.getCommentId());
        return intent;
    }

    @NonNull
    private Intent toTask(ReadListItemBean readListItemBean) {
        Intent intent;
        intent = new Intent(context, TaskDetailsActivity.class);
        intent.putExtra(TaskDetailsActivity.ID, readListItemBean.getId());
        return intent;
    }

    private void readMessage(String id) {
        WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(context);
        parameter.put("operate", "3");
        parameter.put("id", id);
        parameter.put("pageNo", "0");
        parameter.put("rStatus", "0");
        HttpUtilsKt.get(context, URL, parameter, String.class, new HttpCallBack<String>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void onSuccess(String s, String text) {
                EventBus.getDefault().post(ReadFragment.REFRESH);
            }

            @Override
            public void onFailure(String text) {

            }
        });
    }
}
