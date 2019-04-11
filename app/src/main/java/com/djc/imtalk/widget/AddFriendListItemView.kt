package com.djc.imtalk.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.djc.imtalk.R
import com.djc.imtalk.adapter.EMCallBackAdapter
import com.djc.imtalk.data.AddFriendItem
import com.hyphenate.chat.EMClient
import kotlinx.android.synthetic.main.view_add_friend_item.view.*
import org.jetbrains.anko.runOnUiThread
import org.jetbrains.anko.toast

/**
 *@author ： created by dujiangchuan
 * 时间    ：2019/4/11 14
 * 邮箱    ：894230813@qq.com
 */
class AddFriendListItemView(context: Context, attrs: AttributeSet? = null) : RelativeLayout(context, attrs) {
    fun bindView(addFriendItem: AddFriendItem) {
        if (addFriendItem.isAdded) {
            btn_add.isEnabled = false
            btn_add.text = "已添加"
        } else {
            btn_add.isEnabled = true
            btn_add.text = context.getString(R.string.add)
        }

        tv_userName.text = addFriendItem.userName
        tv_create_time.text = addFriendItem.createTime
        btn_add.setOnClickListener {
            addFriend(addFriendItem.userName)
        }
    }

    private fun addFriend(userName: String) {
        EMClient.getInstance().contactManager().aysncAddContact(userName, null, object : EMCallBackAdapter() {
            override fun onSuccess() {
                context.runOnUiThread { toast("发送请求成功") }
            }

            override fun onError(p0: Int, p1: String?) {
                context.runOnUiThread { toast("发送请求失败") }
            }
        })
    }

    init {
        View.inflate(context, R.layout.view_add_friend_item, this)
    }


}