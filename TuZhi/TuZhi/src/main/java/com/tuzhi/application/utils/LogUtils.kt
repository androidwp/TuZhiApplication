package com.tuzhi.application.utils

import android.util.Log

/**
 * Created by wangpeng on 2017/5/19.
 */

val flagTest = false

fun showLog(name: String, text: String) {
    if (flagTest)
        Log.e(name, text)
}