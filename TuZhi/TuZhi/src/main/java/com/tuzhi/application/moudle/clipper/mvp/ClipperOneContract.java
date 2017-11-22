package com.tuzhi.application.moudle.clipper.mvp;

import com.tuzhi.application.bean.ItemBean;
import com.tuzhi.application.moudle.basemvp.BasePresenter;
import com.tuzhi.application.moudle.basemvp.BaseView;

import java.util.ArrayList;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class ClipperOneContract {
    interface View extends BaseView {
        void downLoadFinish(ArrayList<ItemBean> data, boolean haveNextPage, int page);

        void downloadFinish();
    }

    interface Presenter extends BasePresenter<View> {
        void downLoadData(int page);
    }
}
