package com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.checkhistoricalversion.recoverhistoryversion.mvp;

import com.tuzhi.application.moudle.basemvp.BasePresenter;
import com.tuzhi.application.moudle.basemvp.BaseView;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class RecoverHistoryVersionContract {
    interface View extends BaseView {
        void downloadSuccess(String title, String url);

        void recoverSuccess();
    }

    interface Presenter extends BasePresenter<View> {
        void downloadData(String aid, String id);

        void recoverHistory(String aid, String id);
    }
}
