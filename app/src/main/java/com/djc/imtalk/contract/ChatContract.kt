package com.djc.imtalk.contract

import com.hyphenate.chat.EMMessage

/**
 *@author ： created by dujiangchuan
 * 时间    ：2019/4/11 18
 * 邮箱    ：894230813@qq.com
 */
interface ChatContract {
    interface Presenter : BasePresenter {
        fun sendMessage(contact: String, message: String)
        fun addMessage(username: String, p0: MutableList<EMMessage>?)
        fun loadMessages(username: String)
    }

    interface View {
        fun onStartSendMessage()
        fun onSendMessageSuccess()
        fun onSendMessageFailed()
        fun onMessageLoaded()
    }
}