package com.djc.imtalk.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.djc.imtalk.widget.ConversationListItemView

/**
 *@author ： created by dujiangchuan
 * 时间    ：2019/4/11 21
 * 邮箱    ：894230813@qq.com
 */
class ConversationListAdapter(val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        return ConversationListItemViewHolder(ConversationListItemView(context))
    }

    override fun getItemCount(): Int = 30

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
    }

    class ConversationListItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}