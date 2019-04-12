package com.djc.imtalk.presenter

import com.djc.imtalk.adapter.EMCallBackAdapter
import com.djc.imtalk.contract.ChatContract
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMMessage
import org.jetbrains.anko.doAsync

/**
 *@author ： created by dujiangchuan
 * 时间    ：2019/4/11 18
 * 邮箱    ：894230813@qq.com
 */
class ChatPresenter(val view: ChatContract.View) : ChatContract.Presenter {
    companion object {
        val PAGE_SIZE = 10
    }

    val messages = mutableListOf<EMMessage>()

    override fun addMessage(username: String, p0: MutableList<EMMessage>?) {
        //加入到当前的消息列表
        p0?.let { messages.addAll(it) }
        //更新消息为已读消息
        //获取跟联系人的会话，标记绘画的消息为全部已读消息
        val conversation = EMClient.getInstance().chatManager().getConversation(username)
        conversation.markAllMessagesAsRead()
    }


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

    //加载所有的聊天记录条
    override fun loadMessages(username: String) {
        doAsync {
            val conversation = EMClient.getInstance().chatManager().getConversation(username)
            //将加载的消息标记为已读
            conversation.markAllMessagesAsRead()
            messages.addAll(conversation.allMessages)
            uiThread { view.onMessageLoaded() }
        }

    }

    override fun loadMoreMessages(username: String) {
        doAsync {
            val conversation = EMClient.getInstance().chatManager().getConversation(username)
            val startMsgId = messages[0].msgId
            val loadMoreMsgFromDB = conversation.loadMoreMsgFromDB(startMsgId, PAGE_SIZE)
            messages.addAll(0, loadMoreMsgFromDB)
            uiThread {
                view.onMoreMessageLoaded(loadMoreMsgFromDB.size)
            }

        }

    }
}