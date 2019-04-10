package com.djc.imtalk.presenter

import com.djc.imtalk.contract.ContactContract
import com.djc.imtalk.data.ContactListItem
import com.hyphenate.chat.EMClient
import com.hyphenate.exceptions.HyphenateException
import org.jetbrains.anko.doAsync

/**
 *@author ： created by dujiangchuan
 * 时间    ：2019/4/10 19
 * 邮箱    ：894230813@qq.com
 */
class ContactPresenter(val view: ContactContract.View) : ContactContract.Presenter {
    var contactListItems = mutableListOf<ContactListItem>()
    override fun loadContacts() {
        doAsync {
            try {
                val usersList = EMClient.getInstance().contactManager().allContactsFromServer
                //首字符排序
                usersList.sortBy { it[0] }
                usersList.forEach {
                    val contactListItem = ContactListItem(it, it[0].toUpperCase())
                    contactListItems.add(contactListItem)
                }
                uiThread {
                    view.onLoadContactSuccess()
                }
            } catch (e: HyphenateException) {
                uiThread {
                    view.onLoadContactFailed()
                }
            }

        }

    }
}