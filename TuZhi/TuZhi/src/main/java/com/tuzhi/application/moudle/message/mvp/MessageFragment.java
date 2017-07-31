package com.tuzhi.application.moudle.message.mvp;


import android.databinding.ViewDataBinding;

import com.tuzhi.application.R;
import com.tuzhi.application.moudle.basemvp.MVPBaseFragment;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class MessageFragment extends MVPBaseFragment<MessageContract.View, MessagePresenter> implements MessageContract.View {

    @Override
    protected void init(ViewDataBinding viewDataBinding) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_message;
    }
}
