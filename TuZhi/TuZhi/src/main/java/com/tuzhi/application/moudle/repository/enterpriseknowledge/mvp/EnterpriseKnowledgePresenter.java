package com.tuzhi.application.moudle.repository.enterpriseknowledge.mvp;

import android.text.TextUtils;

import com.tuzhi.application.moudle.basemvp.BasePresenterImpl;
import com.tuzhi.application.moudle.repository.enterpriseknowledge.bean.HttpKnowledgeModuleBean;
import com.tuzhi.application.moudle.repository.enterpriseknowledge.bean.KnowledgeCardItemBean;
import com.tuzhi.application.moudle.repository.enterpriseknowledge.item.EnterpriseKnowledgeListItem;
import com.tuzhi.application.utils.HttpCallBack;
import com.tuzhi.application.utils.HttpUtilsKt;

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
 *
 * @author wangpeng
 */

public class EnterpriseKnowledgePresenter extends BasePresenterImpl<EnterpriseKnowledgeContract.View> implements EnterpriseKnowledgeContract.Presenter {

    private final String URL = "tzkm/articleList";
    private final String URL_CHANNEL = "tzkm/knowledgeChannel";

    @Override
    public void downLoadData(String id, String sort, int page) {
        WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(mView.getContext());
        parameter.put("kcId", id);
        parameter.put("sort", sort);
        parameter.put("pageNo", page + "");
        HttpUtilsKt.get(mView.getContext(), URL, parameter, HttpKnowledgeModuleBean.class, new HttpCallBack<HttpKnowledgeModuleBean>() {
            @Override
            public void onFinish() {
                mView.downloadFinishNothing();
            }

            @Override
            public void onSuccess(@Nullable final HttpKnowledgeModuleBean httpKnowledgeModuleBean, @NotNull String text) {
                final ArrayList<KnowledgeCardItemBean> data = new ArrayList<>();
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
                        KnowledgeCardItemBean cardItemBean = new KnowledgeCardItemBean(EnterpriseKnowledgeListItem.TYPE);
                        cardItemBean.setId(resultBean.getId());
                        cardItemBean.setTitle(resultBean.getTitle());
                        cardItemBean.setNickName("贡献者");
                        cardItemBean.setPortrait(resultBean.getAuthor());
                        cardItemBean.setText(resultBean.getSummary());
                        cardItemBean.setCommentNumber(resultBean.getCommentNum());
                        cardItemBean.setFileNumber(resultBean.getFileNum());
                        cardItemBean.setPraiseNumber(resultBean.getPraiseNum());
                        cardItemBean.setPraiseStatus(resultBean.isArticlePraise());
                        ArrayList<String> partners = new ArrayList<>();
                        for (HttpKnowledgeModuleBean.ArticlePageBean.UserInfoBean userInfoBean : resultBean.getPartners()) {
                            partners.add(userInfoBean.getUserImage());
                        }
                        cardItemBean.setJoinPortraits(partners);
                        data.add(cardItemBean);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        mView.downloadFinish(index, next, data);
                    }
                });

            }

            @Override
            public void onFailure(@NotNull String text) {
                if (TextUtils.equals(text, "列表无内容显示")) {
                    mView.downloadFinish(0, false, null);
                }
            }
        });

    }

    @Override
    public void deleteChannel(String klId, String kcId) {
        WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(mView.getContext());
        parameter.put("operate", "4");
        parameter.put("kcId", kcId);
        parameter.put("klId", klId);
        HttpUtilsKt.get(mView.getContext(), URL_CHANNEL, parameter, String.class, new HttpCallBack<String>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void onSuccess(@Nullable String s, @NotNull String text) {
                mView.deleteSuccess();
            }

            @Override
            public void onFailure(@NotNull String text) {

            }
        });
    }

    @Override
    public void renameChannel(String klId, String kcId, final String name) {
        WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(mView.getContext());
        parameter.put("operate", "3");
        parameter.put("klId", klId);
        parameter.put("kcId", kcId);
        parameter.put("name", name);
        HttpUtilsKt.get(mView.getContext(), URL_CHANNEL, parameter, String.class, new HttpCallBack<String>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void onSuccess(@Nullable String s, @NotNull String text) {
                mView.renameSuccess(name);
            }

            @Override
            public void onFailure(@NotNull String text) {

            }
        });
    }
}
