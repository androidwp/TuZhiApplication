package com.tuzhi.application.moudle.knowledgelibtask;

import com.tuzhi.application.moudle.basemvp.BasePresenter;
import com.tuzhi.application.moudle.basemvp.BaseView;
import com.tuzhi.application.moudle.mytasks.MyTasksItemBean;

import java.util.ArrayList;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class KnowledgeLibTaskContract {
    interface View extends BaseView {
        void downloadFinish(ArrayList<MyTasksItemBean> data, boolean haveNextPage, int page);

        void downloadError();
    }

    interface  Presenter extends BasePresenter<KnowledgeLibTaskContract.View> {
        void downloadData(int page);
    }
}
