package com.tuzhi.application.moudle.dynamiccollaboration;

import com.tuzhi.application.moudle.basemvp.BasePresenter;
import com.tuzhi.application.moudle.basemvp.BaseView;

import java.util.ArrayList;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class DynamicCollaborationContract {
    interface View extends BaseView {
        void downloadFinish(ArrayList<DynamicCollaborationItemBean> data, boolean haveNextPage, int page);

        void downloadError();
    }

    interface Presenter extends BasePresenter<View> {
        void downloadData(int page);
    }
}
