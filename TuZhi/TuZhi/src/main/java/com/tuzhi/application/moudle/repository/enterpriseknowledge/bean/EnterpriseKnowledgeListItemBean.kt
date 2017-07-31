package com.tuzhi.application.moudle.repository.enterpriseknowledge.bean

import com.tuzhi.application.bean.BaseListItemBean
import com.tuzhi.application.moudle.repository.enterpriseknowledge.item.EnterpriseKnowledgeListItem

/**
 * Created by wangpeng on 2017/6/1.
 */
data class EnterpriseKnowledgeListItemBean(var mItemType: String = EnterpriseKnowledgeListItem.TYPE, var id: String, var title: String?, var text: String?, var contextNumber: String = "0",
                                           var commentNumber: String = "0", var time: String = "2017-06-02 10:30 更新") : BaseListItemBean(mItemType)