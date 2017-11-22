package com.tuzhi.application.moudle.chooseknowledgelib;

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

public class ChooseKnowledgeLibContract {
    interface View extends BaseView {

        /**
         * 下载完成回掉
         *
         * @param data 下载好传入封装好的数据
         */
        void downLoadFinish(ArrayList<ItemBean> data);
    }

    interface Presenter extends BasePresenter<View> {
        /**
         * 获取数据
         *
         * @param type 传入类型判断是否下载模版
         */
        void downLoadData(String type);
    }
}
