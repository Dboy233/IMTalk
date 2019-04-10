package com.djc.imtalk.adapter

import android.content.Context
import android.support.v7.app.AlertDialog
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.djc.imtalk.R
import com.djc.imtalk.data.ContactListItem
import com.djc.imtalk.ui.activity.ChatActivity
import com.djc.imtalk.widget.ContactListItemView
import com.hyphenate.chat.EMClient
import org.jetbrains.anko.runOnUiThread
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

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
        //点击跳转到聊天界面
        contactListItemView.setOnClickListener { context.startActivity<ChatActivity>("userName" to userName) }
        //长按弹出删除好友对话框
        contactListItemView.setOnLongClickListener {
            var delete_confirm = String.format(context.getString(R.string.delete_frient_confirm), userName)
            AlertDialog.Builder(context).apply {
                setTitle(R.string.delete_friend)
                setMessage(delete_confirm)
                setNegativeButton(context.getString(R.string.cancel), null)
                setPositiveButton(
                    context.getString(R.string.confirm)
                ) { dialogInterface, i ->
                    deleteFriend(userName)
                }
                show()
            }

            true
        }
    }

    private fun deleteFriend(userName: String) {
        //异步方法
        EMClient.getInstance().contactManager().aysncDeleteContact(userName, object : EMCallBackAdapter() {
            override fun onSuccess() {
                context.runOnUiThread { toast("删除成功！") }
            }

            override fun onError(p0: Int, p1: String?) {
                context.runOnUiThread { toast("删除好友失败！") }
            }
        })

    }

    class ContactListItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}