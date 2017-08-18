package com.tuzhi.application.moudle.mine.personalinformation.mvp;

import com.tuzhi.application.bean.HttpInitBean;
import com.tuzhi.application.moudle.basemvp.BasePresenter;
import com.tuzhi.application.moudle.basemvp.BaseView;

import java.io.File;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class PersonalInformationContract {
    interface View extends BaseView {
        void uploadFinish(String imageUrl);

        void downloadSuccess(HttpInitBean httpUserBean);

        void downloadError();
    }

    interface Presenter extends BasePresenter<View> {
        void uploadImage(android.view.View view, String name, File image);

        void downloadUserInfo();
    }
}
