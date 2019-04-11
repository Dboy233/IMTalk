package com.djc.imtalk.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.djc.imtalk.R
import com.hyphenate.chat.EMMessage
import com.hyphenate.chat.EMTextMessageBody
import com.hyphenate.util.DateUtils
import kotlinx.android.synthetic.main.view_send_message_item.view.*
import java.util.*

/**
 *@author ： created by dujiangchuan
 * 时间    ：2019/4/11 17
 * 邮箱    ：894230813@qq.com
 */
class SendMessageItemView(context: Context?, attrs: AttributeSet? = null) : RelativeLayout(context, attrs) {

    fun bindView(emMessage: EMMessage) {
        upDateTime(emMessage)
        upDataMessage(emMessage)
        updateProgress(emMessage)

    }

    private fun updateProgress(emMessage: EMMessage) {
        emMessage.status().let {
            when (it) {
                EMMessage.Status.INPROGRESS -> {
                    progressBar.visibility = View.VISIBLE
                    img_send_failed.visibility = View.GONE
//                    progressBar.animation.start()
                }
                EMMessage.Status.SUCCESS -> {
                    img_send_failed.visibility = View.GONE
                    progressBar.visibility = View.GONE
                }
                EMMessage.Status.FAIL -> {
                    progressBar.visibility = View.GONE
                    img_send_failed.visibility = View.VISIBLE
                }
            }
        }

    }

    private fun upDataMessage(emMessage: EMMessage) {
        if (emMessage.type == EMMessage.Type.TXT) {
            tv_sendMsg.text = (emMessage.body as EMTextMessageBody).message
        } else {
            tv_sendMsg.text = "非文本消息"
        }

    }

    private fun upDateTime(emMessage: EMMessage) {
        tv_talk_time.text = DateUtils.getTimestampString(Date(emMessage.msgTime))

    }


    init {
        View.inflate(context, R.layout.view_send_message_item, this)
    }
}