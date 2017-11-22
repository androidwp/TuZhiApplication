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
        //下载并整理好数据
        void downLoadFinish(ArrayList<ItemBean> data, boolean haveNextPage, int page);

        void downloadFinish();
    }

    interface Presenter extends BasePresenter<View> {
        //下载数据
        void downLoadData(int page);
    }
}
