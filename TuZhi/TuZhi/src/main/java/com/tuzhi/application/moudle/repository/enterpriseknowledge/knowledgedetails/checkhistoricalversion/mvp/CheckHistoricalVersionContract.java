package com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.checkhistoricalversion.mvp;

import com.tuzhi.application.moudle.basemvp.BasePresenter;
import com.tuzhi.application.moudle.basemvp.BaseView;
import com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.checkhistoricalversion.bean.CheckHistoryVersionItemBean;

import java.util.ArrayList;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class CheckHistoricalVersionContract {
    interface View extends BaseView {
        void downloadFinish(ArrayList<CheckHistoryVersionItemBean> data, boolean haveNextPage, int page);

        void downloadFinish();
    }

    interface Presenter extends BasePresenter<View> {
        void downloadData(String id, int page);
    }
}
