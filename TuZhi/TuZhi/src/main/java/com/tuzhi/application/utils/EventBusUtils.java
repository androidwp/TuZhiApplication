package com.tuzhi.application.utils;

import com.tuzhi.application.MainActivity;
import com.tuzhi.application.bean.EventBusBean;
import com.tuzhi.application.moudle.message.mvp.MessageFragment;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by wangpeng on 2017/8/9.
 */

public class EventBusUtils {
    public static void sendUnreadMessageNumber(int number) {
        EventBusBean eventBusBean = new EventBusBean();
        eventBusBean.setName(MainActivity.NAME);
        eventBusBean.setiContent(number);
        EventBus.getDefault().post(eventBusBean);

        EventBusBean busBean = new EventBusBean();
        busBean.setName(MessageFragment.NAME);
        busBean.setiContent(number);
        EventBus.getDefault().post(busBean);
    }
}
