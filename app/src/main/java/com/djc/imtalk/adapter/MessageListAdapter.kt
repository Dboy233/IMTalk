package com.djc.imtalk.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.djc.imtalk.widget.ReceiveMessageItemView
import com.djc.imtalk.widget.SendMessageItemView
import com.hyphenate.chat.EMMessage

/**
 *@author ： created by dujiangchuan
 * 时间    ：2019/4/11 18
 * 邮箱    ：894230813@qq.com
 */
class MessageListAdapter(val context: Context, val message: List<EMMessage>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    //两种消息类型
    companion object {
        val ITEM_TYPE_SEND_MESSAGE = 0 //发送消息
        val ITEM_TYPE_RECEIVE_MESSAGE = 1 //接收消息
    }

    //拿到消息的几种类型  从EMmessage的消息属性中获得是发送还是接收  然后返回view类型
    override fun getItemViewType(position: Int): Int {
        if (message[position].direct() == EMMessage.Direct.SEND) {
            return ITEM_TYPE_SEND_MESSAGE
        } else return ITEM_TYPE_RECEIVE_MESSAGE

    }

    //判断消息的类型，是受类型还是发送的类型
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        if (viewType == ITEM_TYPE_SEND_MESSAGE)
            SendMessageViewHolder(SendMessageItemView(context))
        else
            ReceiveMessageViewHolder(ReceiveMessageItemView(context))
//===================================================================================================

    //获取消息列队个数
    override fun getItemCount(): Int = message.size

    //绑定聊天界面的对话框视图信息 更新
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, pos: Int) {

        if (getItemViewType(pos) == ITEM_TYPE_SEND_MESSAGE) {
            //发送消息绑定
            val sendMessageItemView = holder.itemView as SendMessageItemView
            sendMessageItemView.bindView(message[pos])
        } else {
            val receiveMessageItemView = holder.itemView as ReceiveMessageItemView
            receiveMessageItemView.bindView(message[pos])
        }
    }

    //发送消息的view
    class SendMessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    //接收消息的view
    class ReceiveMessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}