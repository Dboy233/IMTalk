package com.djc.imtalk.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.djc.imtalk.data.ContactListItem
import com.djc.imtalk.ui.activity.ChatActivity
import com.djc.imtalk.widget.ContactListItemView
import org.jetbrains.anko.startActivity

/**
 *@author ： created by dujiangchuan
 * 时间    ：2019/4/10 19
 * 邮箱    ：894230813@qq.com
 */
class ContactListAdapter(
    private val context: Context,
    private val contactListItems: MutableList<ContactListItem>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        return ContactListItemViewHolder(ContactListItemView(context))
    }

    override fun getItemCount(): Int = contactListItems.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val contactListItemView = holder.itemView as ContactListItemView
        contactListItemView.bindView(contactListItems[position])
        val userName = contactListItems[position].userName
        contactListItemView.setOnClickListener { context.startActivity<ChatActivity>("userName" to userName) }
    }

    class ContactListItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}