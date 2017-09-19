package com.tuzhi.application.moudle.mine.setting.settingpush.mvp;

import com.tuzhi.application.moudle.basemvp.BasePresenter;
import com.tuzhi.application.moudle.basemvp.BaseView;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class SettingPushContract {
    interface View extends BaseView {

    }

    interface Presenter extends BasePresenter<View> {
        void commitUpdateStatue(String operate, String status);
    }
}
