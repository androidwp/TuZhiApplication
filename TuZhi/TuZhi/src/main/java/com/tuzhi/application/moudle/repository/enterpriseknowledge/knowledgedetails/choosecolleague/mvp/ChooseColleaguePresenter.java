package com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.choosecolleague.mvp;

import android.text.TextUtils;

import com.tuzhi.application.moudle.basemvp.BasePresenterImpl;
import com.tuzhi.application.moudle.repository.enterpriseknowledge.bean.HttpKnowledgeModuleBean;
import com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.choosecolleague.bean.ChooseColleagueHttpBean;
import com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.choosecolleague.bean.ChooseColleagueItemBean;
import com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.choosecolleague.item.ChooseCardItem;
import com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.choosecolleague.item.ChooseChannelItem;
import com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.choosecolleague.item.ChooseColleagueItem;
import com.tuzhi.application.moudle.repository.knowledgachannel.bean.KnowledgeChannelHttpBean;
import com.tuzhi.application.utils.HttpCallBack;
import com.tuzhi.application.utils.HttpUtilsKt;
import com.tuzhi.application.utils.ToastUtilsKt;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class ChooseColleaguePresenter extends BasePresenterImpl<ChooseColleagueContract.View> implements ChooseColleagueContract.Presenter {

    private static final String URL_COLLEAGUE = "tzkm/share/staffList";

    private static final String URL_SHARE = "tzkm/share/addNoticle";

    private final String URL_CHANNEL = "tzkm/knowledgeChannel";

    private final String URL_CARD = "tzkm/articleList";

    @Override
    public void downloadDataColleague(String cId, int page) {
        WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(mView.getContext());
        parameter.put("aId", cId);
        parameter.put("pageNo", page + "");
        HttpUtilsKt.get(mView.getContext(), URL_COLLEAGUE, parameter, ChooseColleagueHttpBean.class, new HttpCallBack<ChooseColleagueHttpBean>() {
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
    public void downloadDataChannel(final String lId, final int page) {
        WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(mView.getContext());
        parameter.put("klId", lId);
        parameter.put("operate", "1");
        //parameter.put("pageNo", page + "");
        HttpUtilsKt.get(mView.getContext(), URL_CHANNEL, parameter, KnowledgeChannelHttpBean.class, new HttpCallBack<KnowledgeChannelHttpBean>() {
            @Override
            public void onFinish() {
                mView.downloadFinish();
            }

            @Override
            public void onSuccess(@Nullable KnowledgeChannelHttpBean knowledgeChannelHttpBean, @NotNull String text) {
                List<KnowledgeChannelHttpBean.KnowledgeChannelsMapBean> knowledgeChannelsMap = knowledgeChannelHttpBean.getKnowledgeChannelsMap();
                ArrayList<ChooseColleagueItemBean> data = new ArrayList<>();
                for (KnowledgeChannelHttpBean.KnowledgeChannelsMapBean knowledgeChannelsMapBean : knowledgeChannelsMap) {
                    ChooseColleagueItemBean bean = new ChooseColleagueItemBean(ChooseChannelItem.TYPE);
                    bean.setKlid(lId);
                    bean.setKcid(knowledgeChannelsMapBean.getId());
                    bean.setTitle(knowledgeChannelsMapBean.getName());
                    bean.setText(knowledgeChannelsMapBean.getArticleCount() + " 知识卡片");
                    data.add(bean);
                }
                mView.downloadFinish(data, false, 0);
            }

            @Override
            public void onFailure(@NotNull String text) {
                if (TextUtils.equals(text, "列表无内容显示")) {
                    mView.downloadFinish(null, false, 0);
                }
            }
        });
    }

    @Override
    public void downloadDataCare(String channelId, final int page) {
        WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(mView.getContext());
        parameter.put("kcId", channelId);
        parameter.put("pageNo", page + "");
        HttpUtilsKt.get(mView.getContext(), URL_CARD, parameter, HttpKnowledgeModuleBean.class, new HttpCallBack<HttpKnowledgeModuleBean>() {
            @Override
            public void onFinish() {
                mView.downloadFinish();
            }

            @Override
            public void onSuccess(@Nullable final HttpKnowledgeModuleBean httpKnowledgeModuleBean, @NotNull String text) {
                final ArrayList<ChooseColleagueItemBean> data = new ArrayList<>();
                HttpKnowledgeModuleBean.ArticlePageBean articlePage = httpKnowledgeModuleBean.getArticlePage();
                final boolean next = articlePage.isNext();
                final int index = articlePage.getIndex();
                final List<HttpKnowledgeModuleBean.ArticlePageBean.ResultBean> result = articlePage.getResult();
                Observable.fromIterable(result).subscribe(new Observer<HttpKnowledgeModuleBean.ArticlePageBean.ResultBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull HttpKnowledgeModuleBean.ArticlePageBean.ResultBean resultBean) {
                        ChooseColleagueItemBean cardItemBean = new ChooseColleagueItemBean(ChooseCardItem.TYPE);
                        cardItemBean.setId(resultBean.getId());
                        cardItemBean.setTitle(resultBean.getTitle());
                        cardItemBean.setPortrait(resultBean.getAuthor());
                        cardItemBean.setTime(resultBean.getUpdateTime());
                        data.add(cardItemBean);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        mView.downloadFinish(data, next, index);
                    }
                });

            }

            @Override
            public void onFailure(@NotNull String text) {
                if (TextUtils.equals(text, "列表无内容显示")) {
                    mView.downloadFinish(null, false, 0);
                }
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
