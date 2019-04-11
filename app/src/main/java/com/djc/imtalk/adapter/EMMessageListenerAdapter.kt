package com.djc.imtalk.adapter

import com.hyphenate.EMMessageListener
import com.hyphenate.chat.EMMessage

/**
 *@author ： created by dujiangchuan
 * 时间    ：2019/4/11 19
 * 邮箱    ：894230813@qq.com
 */
open class EMMessageListenerAdapter :EMMessageListener {
    override fun onMessageRecalled(p0: MutableList<EMMessage>?) {
    }

    override fun onMessageChanged(p0: EMMessage?, p1: Any?) {
    }

    override fun onCmdMessageReceived(p0: MutableList<EMMessage>?) {
    }

    override fun onMessageReceived(p0: MutableList<EMMessage>?) {
    }

    override fun onMessageDelivered(p0: MutableList<EMMessage>?) {
    }

    override fun onMessageRead(p0: MutableList<EMMessage>?) {
    }
}