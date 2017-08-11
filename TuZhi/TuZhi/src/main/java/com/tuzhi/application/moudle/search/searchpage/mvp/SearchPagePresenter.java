package com.tuzhi.application.moudle.search.searchpage.mvp;

import com.tuzhi.application.moudle.basemvp.BasePresenterImpl;
import com.tuzhi.application.moudle.search.mvp.SearchFragment;
import com.tuzhi.application.moudle.search.searchpage.bean.SearchResultHttpBean;
import com.tuzhi.application.moudle.search.searchpage.bean.SearchResultListBean;
import com.tuzhi.application.moudle.search.searchpage.item.SearchPageNoteItem;
import com.tuzhi.application.moudle.search.searchpage.item.SearchPageSpeakItem;
import com.tuzhi.application.utils.FileUtils;
import com.tuzhi.application.utils.HttpCallBack;
import com.tuzhi.application.utils.HttpUtilsKt;
import com.tuzhi.application.utils.LogUtilsKt;

import java.util.ArrayList;
import java.util.WeakHashMap;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class SearchPagePresenter extends BasePresenterImpl<SearchPageContract.View> implements SearchPageContract.Presenter {
    private static final String URL = "search";

    @Override
    public void downloadData(final String type, int page) {
        //从这里获取要搜索的内容
        //SearchFragment.searchText;
        WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(mView.getContext());
        switch (type) {
            case SearchPageFragment.TYPE_NOTE:
                parameter.put("type", "1");
                break;
            case SearchPageFragment.TYPE_FILE:
                parameter.put("type", "2");
                break;
            default:
                parameter.put("type", "3");
                break;
        }
        parameter.put("keyword", SearchFragment.searchText);
        parameter.put("pageNo", page + "");
        HttpUtilsKt.get(mView.getContext(), URL, parameter, SearchResultHttpBean.class, new HttpCallBack<SearchResultHttpBean>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void onSuccess(SearchResultHttpBean resultHttpBean, String text) {
                LogUtilsKt.showLog(type, text);
                SearchResultHttpBean.ArticlePageBean articlePage = resultHttpBean.getArticlePage();
                int index = articlePage.getIndex();
                boolean next = articlePage.isNext();
                ArrayList<SearchResultListBean> arrayList = new ArrayList<>();
                for (SearchResultHttpBean.ArticlePageBean.ResultBean resultBean : articlePage.getResult()) {
                    switch (type) {
                        case SearchPageFragment.TYPE_NOTE:
                            arrayList.add(dealDataForNote(resultBean));
                            break;
                        case SearchPageFragment.TYPE_FILE:
                            arrayList.add(dealDataForFile(resultBean));
                            break;
                        default:
                            arrayList.add(dealDataForSpeak(resultBean));
                            break;
                    }
                }
                mView.downloadFinish(arrayList, next, index);
            }

            @Override
            public void onFailure(String text) {
                mView.downloadFinish(null, false, 0);
            }
        });
    }

    private SearchResultListBean dealDataForSpeak(SearchResultHttpBean.ArticlePageBean.ResultBean resultBean) {
        SearchResultListBean commentBean = new SearchResultListBean(SearchPageSpeakItem.TYPE);
        commentBean.setAid(resultBean.getArticleId());
        commentBean.setCid(resultBean.getId());
        commentBean.setPortrait(resultBean.getUserImage());
        commentBean.setNickName(resultBean.getNickname());
        commentBean.setTime(resultBean.getTime());
        commentBean.setInfo(resultBean.getContent());
        commentBean.setCommentNumber(resultBean.getReplyNum());
        commentBean.setPraiseNumber(resultBean.getPraiseNum());
        commentBean.setPraiseStatus(resultBean.isUserPraiseStatus());
        commentBean.setArticleTitle(resultBean.getArticleTitle());
        return commentBean;
    }

    private SearchResultListBean dealDataForFile(SearchResultHttpBean.ArticlePageBean.ResultBean resultBean) {
        SearchResultListBean fileBean = new SearchResultListBean(SearchPageNoteItem.TYPE);
        fileBean.setResultType(0);
        fileBean.setAid(resultBean.getArticleId());
        fileBean.setFileId(resultBean.getId());
        fileBean.setTitle(resultBean.getFileName() + "." + resultBean.getFileSuffix());
        fileBean.setFileName(resultBean.getFileName());
        fileBean.setFileType(resultBean.getFileSuffix());
        fileBean.setFileSize(resultBean.getFileSize());
        fileBean.setPreviewUrls(resultBean.getPreviewUrls());
        fileBean.setFileStatus(resultBean.isPreview());
        fileBean.setInfo(resultBean.getAuthor() + "  " + resultBean.getUpdateTime() + "  " + resultBean.getFileSize());
        fileBean.setDownloadStatus(FileUtils.fileExist(mView.getContext(), resultBean.getId()));
        return fileBean;
    }

    private SearchResultListBean dealDataForNote(SearchResultHttpBean.ArticlePageBean.ResultBean resultBean) {
        SearchResultListBean noteBean = new SearchResultListBean(SearchPageNoteItem.TYPE);
        noteBean.setResultType(1);
        noteBean.setAid(resultBean.getId());
        noteBean.setTitle(resultBean.getTitle());
        noteBean.setArticleTitle(resultBean.getTitle());
        noteBean.setInfo(resultBean.getSummary());
        return noteBean;
    }
}
