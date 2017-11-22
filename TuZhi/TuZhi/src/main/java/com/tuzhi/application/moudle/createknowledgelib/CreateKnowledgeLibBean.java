package com.tuzhi.application.moudle.createknowledgelib;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.tuzhi.application.BR;

/**
 * @author wangpeng
 * @date 2017/11/9
 */

public class CreateKnowledgeLibBean extends BaseObservable {
    private String libName;
    private boolean libOpenness = true;
    private String libTypeId = "1";
    private String libTypeName = "公共知识库";
    private String libIcon = "https://ss2.baidu.com/-vo3dSag_xI4khGko9WTAnF6hhy/image/h%3D220/sign=ad0293e246086e0675a8384932097b5a/5ab5c9ea15ce36d3c0a28ce330f33a87e850b1ba.jpg";

    @Bindable
    public boolean isLibOpenness() {
        return libOpenness;
    }

    public void setLibOpenness(boolean libOpenness) {
        this.libOpenness = libOpenness;
        notifyPropertyChanged(BR.libOpenness);
    }

    public String getLibName() {
        return libName;
    }

    public void setLibName(String libName) {
        this.libName = libName;
    }

    public String getLibTypeId() {
        return libTypeId;
    }

    public void setLibTypeId(String libTypeId) {
        this.libTypeId = libTypeId;
    }

    @Bindable
    public String getLibTypeName() {
        return libTypeName;
    }

    public void setLibTypeName(String libTypeName) {
        this.libTypeName = libTypeName;
        notifyPropertyChanged(BR.libTypeName);
    }

    @Bindable
    public String getLibIcon() {
        return libIcon;
    }

    public void setLibIcon(String libIcon) {
        this.libIcon = libIcon;
        notifyPropertyChanged(BR.libIcon);
    }
}
