package com.djc.imtalk.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.djc.imtalk.R
import com.djc.imtalk.data.AddFriendItem
import kotlinx.android.synthetic.main.view_add_friend_item.view.*

/**
 *@author ： created by dujiangchuan
 * 时间    ：2019/4/11 14
 * 邮箱    ：894230813@qq.com
 */
class AddFriendListItemView(context: Context, attrs: AttributeSet? = null) : RelativeLayout(context, attrs) {
    fun bindView(addFriendItem: AddFriendItem) {
        tv_userName.text = addFriendItem.userName
        tv_create_time.text = addFriendItem.createTime
    }

    init {
        View.inflate(context, R.layout.view_add_friend_item, this)
    }


}