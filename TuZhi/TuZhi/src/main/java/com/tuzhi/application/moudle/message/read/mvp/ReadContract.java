package com.tuzhi.application.moudle.message.read.mvp;

import com.tuzhi.application.moudle.basemvp.BasePresenter;
import com.tuzhi.application.moudle.basemvp.BaseView;
import com.tuzhi.application.moudle.message.read.bean.ReadListItemBean;

import java.util.ArrayList;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class ReadContract {
    interface View extends BaseView {
        void downloadFinish(ArrayList<ReadListItemBean> data, boolean haveNextPage, int page);

        void downloadError();
    }

    interface Presenter extends BasePresenter<View> {
        void downloadData(String type, int page);
    }
}
