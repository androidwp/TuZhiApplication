package com.tuzhi.application.moudle.createtask;

import com.tuzhi.application.moudle.basemvp.BasePresenter;
import com.tuzhi.application.moudle.basemvp.BaseView;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 *
 * @author wangpeng
 */

public class CreateTaskContract {
    interface View extends BaseView {
        /**
         * 创建成功
         */
        void createTaskSuccess();
    }

    interface Presenter extends BasePresenter<View> {
        /**
         * 创建任务
         *
         * @param bean
         */
        void createTask(NewTaskBean bean);
    }
}
