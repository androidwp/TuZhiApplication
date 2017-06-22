package com.tuzhi.application.moudle.mine.setting.changepassword.mvp;

import com.tuzhi.application.moudle.basemvp.BasePresenterImpl;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class ChangePasswordPresenter extends BasePresenterImpl<ChangePasswordContract.View> implements ChangePasswordContract.Presenter{

    @Override
    public void commitPassword(String oldPassword, String newPassword) {
        mView.back();
    }
}
