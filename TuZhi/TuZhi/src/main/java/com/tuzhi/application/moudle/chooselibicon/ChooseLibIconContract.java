package com.tuzhi.application.moudle.chooselibicon;

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

public class ChooseLibIconContract {
    interface View extends BaseView {
        /**
         * 下载成功回调
         *
         * @param httpData
         */
        void downloadDataSuccess(ArrayList<ItemBean> httpData);
    }

    interface Presenter extends BasePresenter<View> {
        /**
         * 下载数据
         */
        void downloadData();
    }
}
