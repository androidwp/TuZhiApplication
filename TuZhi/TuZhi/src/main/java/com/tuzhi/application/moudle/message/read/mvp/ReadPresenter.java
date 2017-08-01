package com.tuzhi.application.moudle.message.read.mvp;

import android.os.Handler;

import com.tuzhi.application.bean.EventBusBean;
import com.tuzhi.application.moudle.basemvp.BasePresenterImpl;
import com.tuzhi.application.moudle.message.mvp.MessageFragment;
import com.tuzhi.application.moudle.message.read.bean.ReadListItemBean;
import com.tuzhi.application.moudle.message.read.item.ReadListItem;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class ReadPresenter extends BasePresenterImpl<ReadContract.View> implements ReadContract.Presenter {

    @Override
    public void downloadData(String type, final int page) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ArrayList<ReadListItemBean> arrayList = new ArrayList();
                for (int i = 0; i < 10; i++) {
                    ReadListItemBean readListItemBean = new ReadListItemBean(ReadListItem.TYPE);
                    readListItemBean.setNickName("王凯");
                    readListItemBean.setCommentType(i % 2);
                    readListItemBean.setTime("03-13  23:45");
                    readListItemBean.setContent("好东西就是要分享出来，大家共同成长");
                    readListItemBean.setCommentContent("说的好！");
                    arrayList.add(readListItemBean);
                }
                mView.downloadFinish(arrayList, false, page);
                EventBusBean busBean = new EventBusBean();
                busBean.setName(MessageFragment.NAME);
                busBean.setiContent(4);
                EventBus.getDefault().post(busBean);
            }
        }, 1000);
    }
}
