package com.tuzhi.application.moudle.mine.personalinformation.bindingphoneoremailsecond.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.tuzhi.application.BR;

/**
 * Created by wangpeng on 2017/6/5.
 */

public class BindingPhoneOrEmailSecondBean extends BaseObservable {
    private String type;
    private String title;
    private String text;
    private String noteText;
    private String authCode;

    public BindingPhoneOrEmailSecondBean(String type, String title, String text, String noteText) {
        this.type = type;
        this.title = title;
        this.text = text;
        this.noteText = noteText;
    }

    @Bindable
    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
        notifyPropertyChanged(BR.authCode);
    }

    @Bindable
    public String getNoteText() {
        return noteText;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
        notifyPropertyChanged(BR.noteText);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


}
