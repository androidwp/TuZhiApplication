package com.tuzhi.application.moudle.createchannel;

import com.tuzhi.application.moudle.basemvp.BasePresenter;
import com.tuzhi.application.moudle.basemvp.BaseView;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 * @author wangpeng
 */

public class CreateChannelContract {
    interface View extends BaseView {
        /**
         * 创建频道
         */
        void createChannelSuccess();
    }

    interface Presenter extends BasePresenter<View> {
        /**
         * 创建频道
         * @param bean
         */
        void createChannel(CreateChannelBean bean);
    }
}
