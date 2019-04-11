package com.djc.imtalk.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.djc.imtalk.R

/**
 *@author ： created by dujiangchuan
 * 时间    ：2019/4/11 17
 * 邮箱    ：894230813@qq.com
 */
class ReceiveMessageItemView(context: Context?, attrs: AttributeSet? = null) : RelativeLayout(context, attrs) {
    init {
        View.inflate(context, R.layout.view_receive_message_item, this)
    }
}