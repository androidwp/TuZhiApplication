package com.tuzhi.application.utils

import android.content.Context
import android.os.Handler
import android.os.Message
import android.widget.Toast

/**
 * Created by wangpeng on 2017/5/19.
 */

var flagShowToast = true

var handler = object : Handler() {
    override fun handleMessage(msg: Message?) {
        flagShowToast = true
    }
}

fun toast(context: Context, text: String) {
    if (flagShowToast) {
        flagShowToast = false
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
        handler.sendEmptyMessageDelayed(0, 2000)
    }
}