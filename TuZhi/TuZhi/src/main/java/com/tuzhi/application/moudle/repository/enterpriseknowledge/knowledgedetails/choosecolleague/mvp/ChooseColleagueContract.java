package com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.choosecolleague.mvp;

import com.tuzhi.application.moudle.basemvp.BasePresenter;
import com.tuzhi.application.moudle.basemvp.BaseView;
import com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.choosecolleague.bean.ChooseColleagueItemBean;

import java.util.ArrayList;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class ChooseColleagueContract {
    interface View extends BaseView {
        void downloadFinish(ArrayList<ChooseColleagueItemBean> data, boolean haveNextPage, int page);

        void downloadFinish();

        void shareCardSuccess();
    }

    interface Presenter extends BasePresenter<View> {
        void downloadDataColleague(String cId, int page);

        void downloadDataChannel(String lId, int page);

        void downloadDataCare(String channelId, int page);

        void shareCard(String cid, String staffIds, String content);
    }
}
