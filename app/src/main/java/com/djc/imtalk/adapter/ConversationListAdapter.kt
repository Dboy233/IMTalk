package com.djc.imtalk.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.djc.imtalk.widget.ConversationListItemView
import com.hyphenate.chat.EMConversation

/**
 *@author ： created by dujiangchuan
 * 时间    ：2019/4/11 21
 * 邮箱    ：894230813@qq.com
 */
class ConversationListAdapter(
    val context: Context,
    val conversations: MutableList<EMConversation>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        return ConversationListItemViewHolder(ConversationListItemView(context))
    }

    override fun getItemCount(): Int = conversations.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, pos: Int) {
        val conversationListItemView = holder.itemView as ConversationListItemView
        conversationListItemView.bindView(conversations[pos])
    }

    class ConversationListItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}