package com.tuzhi.application.moudle.search.bean;

import com.tuzhi.application.bean.BaseListItemBean;

/**
 * Created by wangpeng on 2017/8/1.
 */

public class SearchHistoryBean extends BaseListItemBean {

    private String searchContent;
    private int historyNumber;

    public SearchHistoryBean(String itemType) {
        super(itemType);
    }

    public int getHistoryNumber() {
        return historyNumber;
    }

    public void setHistoryNumber(int historyNumber) {
        this.historyNumber = historyNumber;
    }

    public String getSearchContent() {
        return searchContent;
    }

    public void setSearchContent(String searchContent) {
        this.searchContent = searchContent;
    }
}
