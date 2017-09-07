package com.tuzhi.application.moudle.mine.setting.changeinitialpassword.mvp;

import com.tuzhi.application.moudle.basemvp.BasePresenterImpl;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class ChangeInitialPasswordPresenter extends BasePresenterImpl<ChangeInitialPasswordContract.View> implements ChangeInitialPasswordContract.Presenter{

    @Override
    public void commitPassword(String password, String againPassword) {
        mView.changeSuccess();
    }
}
