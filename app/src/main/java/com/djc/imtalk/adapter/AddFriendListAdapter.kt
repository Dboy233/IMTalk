package com.djc.imtalk.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.djc.imtalk.data.AddFriendItem
import com.djc.imtalk.widget.AddFriendListItemView

/**
 *@author ： created by dujiangchuan
 * 时间    ：2019/4/11 14
 * 邮箱    ：894230813@qq.com
 */
class AddFriendListAdapter(
    val context: Context,
    val addFriendItems: MutableList<AddFriendItem>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        return AddFriendListItemViewHolder(AddFriendListItemView(context))

    }

    override fun getItemCount(): Int = addFriendItems.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, pos: Int) {
        val itemView = holder.itemView as AddFriendListItemView
        itemView.bindView(addFriendItems[pos])

    }


    class AddFriendListItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}