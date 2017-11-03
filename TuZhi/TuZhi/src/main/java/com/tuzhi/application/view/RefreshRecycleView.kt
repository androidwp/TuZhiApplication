package com.tuzhi.application.view

import android.content.Context
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.tuzhi.application.R
import com.tuzhi.application.bean.BaseListItemBean
import com.tuzhi.application.item.GeneralEmptyFootViewItem
import com.tuzhi.application.item.GeneralLoadFootViewItem
import kale.adapter.CommonRcvAdapter

/**
 * Created by wangpeng on 2017/5/31.
 */

interface LoadMoreListener {
    fun loadMoreListener(page: Int)
}

class RefreshRecycleView : FrameLayout {
    lateinit private var srl: SwipeRefreshLayout
    lateinit var rv: RecyclerView
    lateinit private var ll: LinearLayout
    lateinit private var tvTitle: TextView
    lateinit private var tvInfo: TextView
    lateinit private var iv: ImageView
    lateinit private var adapter: RecyclerView.Adapter<CommonRcvAdapter.RcvAdapterItem>
    lateinit private var refreshListener: SwipeRefreshLayout.OnRefreshListener
    lateinit var loadListener: LoadMoreListener
    var flagHaveNextPage = false
    var flagLoading = false
    var page = 0
    var title = ""
    var info = ""

    constructor(context: Context) : this(context, null)



    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    private fun init() {
        View.inflate(context, R.layout.view_refresh_recycle, this)
        srl = findViewById(R.id.srl) as SwipeRefreshLayout
        rv = findViewById(R.id.rv) as RecyclerView
        ll = findViewById(R.id.ll) as LinearLayout
        tvTitle = findViewById(R.id.tvTitle) as TextView
        tvInfo = findViewById(R.id.tvInfo) as TextView
        iv = findViewById(R.id.iv) as ImageView
        rv.layoutManager = LinearLayoutManager(context)
        srl.setColorSchemeResources(R.color.colorBlueButton)
        rv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val canScrollVertically = recyclerView.canScrollVertically(1)
                if (!canScrollVertically && flagHaveNextPage && !flagLoading) {
                    flagLoading = true
                    loadListener.loadMoreListener(++page)
                }
            }
        })
    }

    fun setDrawable(drawableId: Int) {
        iv.setImageResource(drawableId)
    }

    fun setOnRefreshListener(refreshListener: SwipeRefreshLayout.OnRefreshListener) {
        this.refreshListener = refreshListener
        srl.setOnRefreshListener(refreshListener)
    }

    fun setAdapter(adapter: RecyclerView.Adapter<CommonRcvAdapter.RcvAdapterItem>) {
        this.adapter = adapter
        rv.adapter = adapter
    }

    fun notifyDataSetChanged() {
        rv.adapter.notifyDataSetChanged()
    }

    fun isShowRefreshView(flag: Boolean) {
        if (flag) {
            srl.post({
                flagLoading = true
                srl.isRefreshing = true
                refreshListener.onRefresh()
            })
        } else {
            flagLoading = false
            srl.isRefreshing = false
        }
    }

    /**
     * mData 原数据
     * mDataSource 新数据
     */

    fun <T : BaseListItemBean> downLoadFinish(page: Int, haveNextPage: Boolean, mData: ArrayList<T>, mDataSource: ArrayList<T>?, addEmptyFoot: Boolean) {
        this.flagHaveNextPage = haveNextPage
        flagLoading = false
        isShowRefreshView(false)
        this.page = page
        if (page == 0) {
            mData.clear()
        }
        if (mDataSource != null && mDataSource.size > 0) {
            if (mData.size > 0) {
                val itemBean = mData[mData.size - 1]
                if (TextUtils.equals(itemBean.itemType, GeneralLoadFootViewItem.TYPE) && !haveNextPage) {
                    mData.removeAt(mData.size - 1)
                    mData.addAll(mDataSource)
                    if (addEmptyFoot) {
                        itemBean.itemType = GeneralEmptyFootViewItem.TYPE
                        mData.add(itemBean)
                    }
                } else if (!TextUtils.equals(itemBean.itemType, GeneralLoadFootViewItem.TYPE) && haveNextPage) {
                    val clone = itemBean.clone()
                    clone.itemType = GeneralLoadFootViewItem.TYPE
                    mData.add(clone as T)
                } else if (TextUtils.equals(itemBean.itemType, GeneralLoadFootViewItem.TYPE) && haveNextPage) {
                    mData.addAll(mData.size - 1, mDataSource)
                }
            } else {
                mData.addAll(mDataSource)
                if (mData.size > 0) {
                    val itemBean = mData[0]
                    val clone = itemBean.clone()
                    if (haveNextPage) {
                        clone.itemType = GeneralLoadFootViewItem.TYPE
                        mData.add(clone as T)
                    } else {
                        if (addEmptyFoot) {
                            clone.itemType = GeneralEmptyFootViewItem.TYPE
                            mData.add(clone as T)
                        }
                    }
                }
            }
        }

        if (page == 0 && mDataSource == null) {
            ll.visibility = View.VISIBLE
            tvTitle.text = title
            tvInfo.text = info
        } else {
            ll.visibility = View.GONE
        }
        notifyDataSetChanged()
    }
}


