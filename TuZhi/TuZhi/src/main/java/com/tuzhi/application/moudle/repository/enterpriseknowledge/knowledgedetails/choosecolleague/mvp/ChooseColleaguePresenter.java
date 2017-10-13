package com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.choosecolleague.mvp;

import android.text.TextUtils;

import com.tuzhi.application.moudle.basemvp.BasePresenterImpl;
import com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.choosecolleague.bean.ChooseColleagueHttpBean;
import com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.choosecolleague.bean.ChooseColleagueItemBean;
import com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.choosecolleague.item.ChooseColleagueItem;
import com.tuzhi.application.utils.HttpCallBack;
import com.tuzhi.application.utils.HttpUtilsKt;
import com.tuzhi.application.utils.ToastUtilsKt;

import java.util.ArrayList;
import java.util.WeakHashMap;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class ChooseColleaguePresenter extends BasePresenterImpl<ChooseColleagueContract.View> implements ChooseColleagueContract.Presenter {

    private static final String URL = "tzkm/share/staffList";

    private static final String URL_SHARE = "tzkm/share/addNoticle";

    @Override
    public void downloadData(String cId, int page) {

        WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(mView.getContext());
        parameter.put("aId", cId);
        parameter.put("pageNo", page + "");
        HttpUtilsKt.get(mView.getContext(), URL, parameter, ChooseColleagueHttpBean.class, new HttpCallBack<ChooseColleagueHttpBean>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void onSuccess(ChooseColleagueHttpBean colleagueHttpBean, String text) {
                ChooseColleagueHttpBean.CommentPageBean commentPage = colleagueHttpBean.getStaffPage();
                int index = commentPage.getIndex();
                boolean next = commentPage.isNext();
                ArrayList<ChooseColleagueItemBean> arrayList = new ArrayList<>();
                for (ChooseColleagueHttpBean.CommentPageBean.ResultBean resultBean : commentPage.getResult()) {
                    ChooseColleagueItemBean chooseColleagueItemBean = new ChooseColleagueItemBean(ChooseColleagueItem.TYPE);
                    chooseColleagueItemBean.setId(resultBean.getId());
                    chooseColleagueItemBean.setNickName(resultBean.getNickname());
                    chooseColleagueItemBean.setPortrait(resultBean.getUserImage());
                    chooseColleagueItemBean.setChooseStatus(false);
                    arrayList.add(chooseColleagueItemBean);
                }
                mView.downloadFinish(arrayList, next, index);
            }

            @Override
            public void onFailure(String text) {

            }
        });
    }

    @Override
    public void shareCard(String cid, String staffIds, String content) {
        WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(mView.getContext());
        parameter.put("aId", cid);
        parameter.put("staffIds", staffIds);
        if (!TextUtils.isEmpty(content))
            parameter.put("content", content);
        HttpUtilsKt.get(mView.getContext(), URL_SHARE, parameter, String.class, new HttpCallBack<String>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void onSuccess(String s, String text) {
                ToastUtilsKt.toast(mView.getContext(), "分享成功");
                mView.shareCardSuccess();
            }

            @Override
            public void onFailure(String text) {

            }
        });
    }
}
