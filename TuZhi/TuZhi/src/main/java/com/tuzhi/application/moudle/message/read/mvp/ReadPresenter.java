package com.tuzhi.application.moudle.message.read.mvp;

import com.tuzhi.application.moudle.basemvp.BasePresenterImpl;
import com.tuzhi.application.moudle.message.read.bean.ReadListItemBean;
import com.tuzhi.application.moudle.message.read.bean.ReadUnreadListHttpBean;
import com.tuzhi.application.moudle.message.read.item.ReadListItem;
import com.tuzhi.application.utils.EventBusUtils;
import com.tuzhi.application.utils.HttpCallBack;
import com.tuzhi.application.utils.HttpUtilsKt;

import java.util.ArrayList;
import java.util.WeakHashMap;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class ReadPresenter extends BasePresenterImpl<ReadContract.View> implements ReadContract.Presenter {

    private static final String URL = "user/notice";

    @Override
    public void downloadData(final String type, final int page) {
        WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(mView.getContext());
        parameter.put("pageNo", page + "");
        parameter.put("operate", "1");
        parameter.put("rStatus", type.equals(ReadFragment.TYPE_READ) ? "0" : "1");
        HttpUtilsKt.get(mView.getContext(), URL, parameter, ReadUnreadListHttpBean.class, new HttpCallBack<ReadUnreadListHttpBean>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void onSuccess(ReadUnreadListHttpBean readUnreadListHttpBean, String text) {
                ReadUnreadListHttpBean.NoticePageBean noticePage = readUnreadListHttpBean.getNoticePage();
                if (noticePage != null) {
                    int index = noticePage.getIndex();
                    boolean next = noticePage.isNext();
                    ArrayList<ReadListItemBean> arrayList = new ArrayList();
                    for (ReadUnreadListHttpBean.NoticePageBean.ResultBean resultBean : noticePage.getResult()) {
                        ReadListItemBean readListItemBean = new ReadListItemBean(ReadListItem.TYPE);
                        readListItemBean.setId(resultBean.getId());
                        readListItemBean.setPortrait(resultBean.getUserImage());
                        readListItemBean.setNickName(resultBean.getNickname());
                        readListItemBean.setContent(resultBean.getSourceContent());
                        readListItemBean.setTime(resultBean.getTime());
                        readListItemBean.setDescription(resultBean.getDescription());
                        readListItemBean.setCommentContent(resultBean.getContent());
                        readListItemBean.setContentType(resultBean.getType());
                        readListItemBean.setArticleId(resultBean.getArticleId());
                        readListItemBean.setArticleTitle(resultBean.getArticleTitle());
                        readListItemBean.setCommentId(resultBean.getCommentId());
                        readListItemBean.setLimit(resultBean.isLimit());
                        arrayList.add(readListItemBean);
                    }
                    if (page == 0 && noticePage.getResult().size() == 0) {
                        mView.downloadFinish(null, false, index);
                    } else {
                        mView.downloadFinish(arrayList, next, index);
                    }
                    if (type.equals(ReadFragment.TYPE_UNREAD)) {
                        EventBusUtils.sendUnreadMessageNumber(noticePage.getCount());
                    }
                } else {
                    mView.downloadFinish(null, false, 0);
                    if (type.equals(ReadFragment.TYPE_UNREAD)) {
                        EventBusUtils.sendUnreadMessageNumber(0);
                    }
                }
            }

            @Override
            public void onFailure(String text) {
                mView.downloadError();
            }
        });
    }
}
