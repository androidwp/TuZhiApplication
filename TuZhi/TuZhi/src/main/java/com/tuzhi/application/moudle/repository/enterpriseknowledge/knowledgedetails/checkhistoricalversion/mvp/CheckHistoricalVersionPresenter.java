package com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.checkhistoricalversion.mvp;

import android.text.TextUtils;

import com.tuzhi.application.moudle.basemvp.BasePresenterImpl;
import com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.checkhistoricalversion.bean.CheckHistoryVersionHttpBean;
import com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.checkhistoricalversion.bean.CheckHistoryVersionItemBean;
import com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.checkhistoricalversion.item.CheckHistoryVersionItem;
import com.tuzhi.application.utils.HttpCallBack;
import com.tuzhi.application.utils.HttpUtilsKt;

import java.util.ArrayList;
import java.util.WeakHashMap;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class CheckHistoricalVersionPresenter extends BasePresenterImpl<CheckHistoricalVersionContract.View> implements CheckHistoricalVersionContract.Presenter {

    public static final String URL = "tzkm/historyVersion";

    @Override
    public void downloadData(final String aid, final int page) {
        WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(mView.getContext());
        parameter.put("operate", "1");
        parameter.put("pageNo", page + "");
        parameter.put("aId", aid);
        HttpUtilsKt.get(mView.getContext(), URL, parameter, CheckHistoryVersionHttpBean.class, new HttpCallBack<CheckHistoryVersionHttpBean>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void onSuccess(CheckHistoryVersionHttpBean checkHistoryVersionHttpBean, String text) {
                CheckHistoryVersionHttpBean.EditRecordPageBean editRecordPage = checkHistoryVersionHttpBean.getEditRecordPage();
                int index = editRecordPage.getIndex();
                boolean next = editRecordPage.isNext();
                ArrayList<CheckHistoryVersionItemBean> data = new ArrayList<>();
                for (CheckHistoryVersionHttpBean.EditRecordPageBean.ResultBean resultBean : editRecordPage.getResult()) {
                    CheckHistoryVersionItemBean checkHistoryVersionItemBean = new CheckHistoryVersionItemBean(CheckHistoryVersionItem.TYPE);
                    checkHistoryVersionItemBean.setId(resultBean.getId());
                    checkHistoryVersionItemBean.setNickName(resultBean.getAuthor());
                    checkHistoryVersionItemBean.setPortrait(resultBean.getAuthorImg());
                    checkHistoryVersionItemBean.setInfo(resultBean.getUpdateTime());
                    checkHistoryVersionItemBean.setAid(aid);
                    data.add(checkHistoryVersionItemBean);
                }
                mView.downloadFinish(data, next, index);
            }

            @Override
            public void onFailure(String text) {
                mView.downloadFinish();
                if (TextUtils.equals(text, "列表无内容显示")) {
                    mView.downloadFinish(null, false, 0);
                }
            }
        });
    }
}
