package com.djc.imtalk.adapter

import com.hyphenate.EMContactListener

/**
 *@author ： created by dujiangchuan
 * 时间    ：2019/4/10 21
 * 邮箱    ：894230813@qq.com
 */

open class EMContactListenerAdapter :EMContactListener {
    override fun onContactInvited(p0: String?, p1: String?) {
    }

    override fun onContactDeleted(p0: String?) {
    }

    override fun onFriendRequestAccepted(p0: String?) {
    }

    override fun onContactAdded(p0: String?) {
    }

    override fun onFriendRequestDeclined(p0: String?) {
    }
}