package com.tuzhi.application.moudle.repository.mvp;

import com.tuzhi.application.bean.ItemBean;
import com.tuzhi.application.moudle.basemvp.BasePresenter;
import com.tuzhi.application.moudle.basemvp.BaseView;

import java.util.ArrayList;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 *
 * @author wangpeng
 */

public class RepositoryContract {
    interface View extends BaseView {
        /**
         * 下载数据回调
         *
         * @param data
         * @param haveNextPage
         * @param page
         */
        void downLoadFinish(ArrayList<ItemBean> data, boolean haveNextPage, int page);

        /**
         * 下载完成回调
         */
        void downloadFinish();
    }

    interface Presenter extends BasePresenter<View> {
        /**
         * 下载数据
         *
         * @param page
         */
        void downLoadData(int page);
    }
}
