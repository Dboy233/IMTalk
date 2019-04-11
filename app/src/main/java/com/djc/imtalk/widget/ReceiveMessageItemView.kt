package com.djc.imtalk.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.djc.imtalk.R
import com.hyphenate.chat.EMMessage
import com.hyphenate.chat.EMTextMessageBody
import com.hyphenate.util.DateUtils
import kotlinx.android.synthetic.main.view_receive_message_item.view.*
import java.util.*

/**
 *@author ： created by dujiangchuan
 * 时间    ：2019/4/11 17
 * 邮箱    ：894230813@qq.com
 */
class ReceiveMessageItemView(context: Context?, attrs: AttributeSet? = null) : RelativeLayout(context, attrs) {
    fun bindView(emMessage: EMMessage) {
        upDataMessage(emMessage)
        upDateTime(emMessage)
    }

    init {
        View.inflate(context, R.layout.view_receive_message_item, this)
    }

    private fun upDataMessage(emMessage: EMMessage) {
        if (emMessage.type == EMMessage.Type.TXT) {
            tv_receiveMsg.text = (emMessage.body as EMTextMessageBody).message
        } else {
            tv_receiveMsg.text = "非文本消息"
        }

    }

    private fun upDateTime(emMessage: EMMessage) {
        tv_talk_time.text = DateUtils.getTimestampString(Date(emMessage.msgTime))

    }
}