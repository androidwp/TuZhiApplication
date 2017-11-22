package com.tuzhi.application.utils;

import com.tuzhi.application.MainActivity;
import com.tuzhi.application.bean.EventBusBean;
import com.tuzhi.application.moudle.completedtasks.CompletedTasksActivity;
import com.tuzhi.application.moudle.knowledgelibtask.KnowledgeLibTaskFragment;
import com.tuzhi.application.moudle.message.mvp.MessageFragment;
import com.tuzhi.application.moudle.mytasks.MyTasksFragment;

import org.greenrobot.eventbus.EventBus;

/**
 * @author wangpeng
 * @date 2017/8/9
 */

public class EventBusUtils {
    /**
     * 消息数变更通知
     *
     * @param number
     */
    public static void sendUnreadMessageNumber(int number) {
        EventBusBean eventBusBean = new EventBusBean();
        eventBusBean.setName(MainActivity.NAME);
        eventBusBean.setEventType(MainActivity.TYPE_NOTIFICATION);
        eventBusBean.setiContent(number);
        EventBus.getDefault().post(eventBusBean);

        EventBusBean busBean = new EventBusBean();
        busBean.setName(MessageFragment.NAME);
        busBean.setiContent(number);
        EventBus.getDefault().post(busBean);
    }

    public static void taskStatueChange() {
        EventBus.getDefault().post(KnowledgeLibTaskFragment.EVENT_REFRESH);
        EventBus.getDefault().post(CompletedTasksActivity.EVENT_REFRESH);
        EventBus.getDefault().post(MyTasksFragment.EVENT_REFRESH);
    }
}
