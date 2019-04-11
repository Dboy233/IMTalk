package com.djc.imtalk.presenter

import com.djc.imtalk.adapter.EMCallBackAdapter
import com.djc.imtalk.contract.ChatContract
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMMessage

/**
 *@author ： created by dujiangchuan
 * 时间    ：2019/4/11 18
 * 邮箱    ：894230813@qq.com
 */
class ChatPresenter(val view: ChatContract.View) : ChatContract.Presenter {

    val messages = mutableListOf<EMMessage>()


    override fun sendMessage(contact: String, message: String) {
        //创建一条消息
        val emMessage = EMMessage.createTxtSendMessage(message, contact)
        emMessage.setMessageStatusCallback(object : EMCallBackAdapter() {
            override fun onSuccess() {
                uiThread { view.onSendMessageSuccess() }
            }

            override fun onError(p0: Int, p1: String?) {
                uiThread { view.onSendMessageFailed() }

            }
        })


        //添加到消息列队
        messages.add(emMessage)
        //通知view层开始发送
        view.onStartSendMessage()
        //调用api发送信息
        EMClient.getInstance().chatManager().sendMessage(emMessage)


    }


}