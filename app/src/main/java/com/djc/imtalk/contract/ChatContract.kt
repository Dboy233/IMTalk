package com.djc.imtalk.contract

/**
 *@author ： created by dujiangchuan
 * 时间    ：2019/4/11 18
 * 邮箱    ：894230813@qq.com
 */
interface ChatContract {
    interface Presenter : BasePresenter {
        fun sendMessage(contact: String, message: String)
    }

    interface View {
        fun onStartSendMessage()
        fun onSendMessageSuccess()
        fun onSendMessageFailed()
    }
}