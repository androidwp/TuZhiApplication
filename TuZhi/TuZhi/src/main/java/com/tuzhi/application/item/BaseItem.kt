package com.tuzhi.application.item

import android.content.Context
import android.view.View
import kale.adapter.item.AdapterItem

/**
 * Created by wangpeng on 2017/5/19.
 */

abstract class BaseItem<T> : AdapterItem<T> {

    lateinit var context : Context

    override fun setViews() {

    }

    override fun bindViews(view: View) {
        context=view.context
        bindView(view)
    }

    abstract fun bindView(view: View)
}
