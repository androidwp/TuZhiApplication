package com.tuzhi.application.moudle.repository.crepository.mvp;

import com.tuzhi.application.moudle.basemvp.BasePresenter;
import com.tuzhi.application.moudle.basemvp.BaseView;
import com.tuzhi.application.moudle.repository.crepository.bean.HttpCreateCardBean;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class CrepositoryContract {
    interface View extends BaseView {
        /**
         * 创建成功回调
         *
         * @param bean
         */
        void commitFinish(HttpCreateCardBean bean);

        void commitError();
    }

    interface Presenter extends BasePresenter<View> {
        /**
         * 创建卡片
         *
         * @param name
         * @param channelId 频道id
         */
        void commit(String name, String channelId);
    }
}
