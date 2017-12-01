package com.tuzhi.application.moudle.applyfortrial;

import com.tuzhi.application.moudle.basemvp.BasePresenter;
import com.tuzhi.application.moudle.basemvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 * @author wangpeng
 */

public class ApplyForTrialContract {
    interface View extends BaseView {
        void commitSuccess();
    }

    interface  Presenter extends BasePresenter<View> {
        void commit(ApplyForTrialBean bean);
    }
}
