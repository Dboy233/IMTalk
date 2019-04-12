package com.djc.imtalk.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.djc.imtalk.R
import com.hyphenate.chat.EMConversation
import com.hyphenate.chat.EMMessage
import com.hyphenate.chat.EMTextMessageBody
import com.hyphenate.util.DateUtils
import kotlinx.android.synthetic.main.view_conversation_item.view.*
import java.util.*

/**
 *@author ： created by dujiangchuan
 * 时间    ：2019/4/11 20
 * 邮箱    ：894230813@qq.com
 */
class ConversationListItemView(context: Context?, attrs: AttributeSet? = null) : RelativeLayout(context, attrs) {
    fun bindView(emConversation: EMConversation) {
        tv_userName.text = emConversation.conversationId()
        if (emConversation.lastMessage.type == EMMessage.Type.TXT) {
            val body = emConversation.lastMessage.body as EMTextMessageBody
            tv_lastMessage.text = body.message

        } else tv_lastMessage.text = "非文本消息"
        val timestampString = DateUtils.getTimestampString(Date(emConversation.lastMessage.msgTime))
        tv_time.text = timestampString
        if (emConversation.unreadMsgCount > 0) {
            tv_unreadCount.visibility = View.VISIBLE
            tv_unreadCount.text = emConversation.unreadMsgCount.toString()
        } else {
            tv_unreadCount.visibility = View.GONE
        }
    }

    init {
        View.inflate(context, R.layout.view_conversation_item, this)
    }
}