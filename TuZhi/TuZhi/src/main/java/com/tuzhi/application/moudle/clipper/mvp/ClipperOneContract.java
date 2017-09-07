package com.tuzhi.application.moudle.clipper.mvp;

import com.tuzhi.application.moudle.basemvp.BasePresenter;
import com.tuzhi.application.moudle.basemvp.BaseView;
import com.tuzhi.application.moudle.repository.bean.RepositoryListItemBean;

import java.util.ArrayList;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class ClipperOneContract {
    interface View extends BaseView {
        void downLoadFinish(ArrayList<RepositoryListItemBean> data, boolean haveNextPage, int page);

        void downloadFinish();
    }

    interface Presenter extends BasePresenter<View> {
        void downLoadData(int page);
    }
}
