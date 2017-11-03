package com.tuzhi.application.moudle.mytasks;

import com.tuzhi.application.bean.BaseListItemBean;

/**
 * Created by wangpeng on 2017/10/30.
 */

public class MyTestsItemBean extends BaseListItemBean{
    private String id;
    private String content;
    private boolean checkStatue;

    public MyTestsItemBean(String itemType) {
        super(itemType);
    }

    public boolean isCheckStatue() {
        return checkStatue;
    }

    public void setCheckStatue(boolean checkStatue) {
        this.checkStatue = checkStatue;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
