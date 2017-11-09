package com.tuzhi.application.moudle.chooseknowledgelib;

import com.tuzhi.application.bean.ItemBean;
import com.tuzhi.application.moudle.basemvp.BasePresenter;
import com.tuzhi.application.moudle.basemvp.BaseView;

import java.util.ArrayList;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class ChooseKnowledgeLibContract {
    interface View extends BaseView {
        //下载并整理好数据
        void downLoadFinish(ArrayList<ItemBean> data);
    }

    interface Presenter extends BasePresenter<View> {
        void downLoadData();
    }
}
