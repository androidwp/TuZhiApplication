package com.tuzhi.application.moudle.chooselibicon;

import com.tuzhi.application.bean.ItemBean;
import com.tuzhi.application.moudle.basemvp.BasePresenterImpl;
import com.tuzhi.application.utils.HttpCallBack;
import com.tuzhi.application.utils.HttpUtilsKt;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 *
 * @author wangpeng
 */

public class ChooseLibIconPresenter extends BasePresenterImpl<ChooseLibIconContract.View> implements ChooseLibIconContract.Presenter {

    private static final String URL = "tzkm/getImageList";

    @Override
    public void downloadData() {
        WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(mView.getContext());
        parameter.put("pageNo", "0");
        HttpUtilsKt.get(mView.getContext(), URL, parameter, HttpImagesBean.class, new HttpCallBack<HttpImagesBean>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void onSuccess(@Nullable HttpImagesBean httpImagesBean, @NotNull String text) {
                List<String> imagelist = httpImagesBean.getImagelist();
                ArrayList<ItemBean> itemBeans = new ArrayList<>();
                for (String s : imagelist) {
                    ItemBean itemBean = new ItemBean(ChooseLibIconItem.TYPE);
                    itemBean.setImage(s);
                    itemBeans.add(itemBean);
                }
                mView.downloadDataSuccess(itemBeans);
            }

            @Override
            public void onFailure(@NotNull String text) {

            }
        });
    }
}
