package com.tuzhi.application.moudle.mytasks;

import com.tuzhi.application.moudle.basemvp.BasePresenter;
import com.tuzhi.application.moudle.basemvp.BaseView;

import java.util.ArrayList;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class MyTasksContract {
    interface View extends BaseView {
        void downloadFinish(ArrayList<MyTestsItemBean> data, boolean haveNextPage, int page);

        void downloadError();
    }

    interface  Presenter extends BasePresenter<View> {
        void downloadData(int page);
    }
}
