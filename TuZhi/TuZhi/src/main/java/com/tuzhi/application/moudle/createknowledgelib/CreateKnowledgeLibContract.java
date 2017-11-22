package com.tuzhi.application.moudle.createknowledgelib;

import com.tuzhi.application.moudle.basemvp.BasePresenter;
import com.tuzhi.application.moudle.basemvp.BaseView;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 *
 * @author wangpeng
 */

public class CreateKnowledgeLibContract {
    interface View extends BaseView {
        /**
         * 创建成功回调
         */
        void createLibSuccess();
    }

    interface Presenter extends BasePresenter<View> {
        /**
         * 创建数据库
         *
         * @param type 判断是修改还是创建
         * @param bean 创建信息
         */
        void createLib(String type, CreateKnowledgeLibBean bean);
    }
}
