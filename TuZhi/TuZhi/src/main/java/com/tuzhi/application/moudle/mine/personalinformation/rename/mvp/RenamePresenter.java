package com.tuzhi.application.moudle.mine.personalinformation.rename.mvp;

import com.tuzhi.application.moudle.basemvp.BasePresenterImpl;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class RenamePresenter extends BasePresenterImpl<RenameContract.View> implements RenameContract.Presenter{

    @Override
    public void commitName(String name) {
        mView.back();
    }
}
