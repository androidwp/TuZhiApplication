package com.tuzhi.application.utils;

import android.content.Context;
import android.os.Handler;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by wangpeng on 2017/6/27.
 */

public class KeyBoardUtils {
    public static void showKeyBoard(final Context context){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                InputMethodManager mInputManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                mInputManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }, 100);
    }
}
