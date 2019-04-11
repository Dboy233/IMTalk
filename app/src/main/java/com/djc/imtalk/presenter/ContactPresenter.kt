package com.djc.imtalk.presenter

import com.djc.imtalk.contract.ContactContract
import com.djc.imtalk.data.ContactListItem
import com.djc.imtalk.data.db.Contact
import com.djc.imtalk.data.db.IMDatabase
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
            //初始化集合
            contactListItems.clear()
            //清空数据库
            IMDatabase.instance.deleteAllContacts()
            try {
                val usersList = EMClient.getInstance().contactManager().allContactsFromServer
                //首字符排序
                usersList.sortBy { it[0] }
                usersList.forEachIndexed { index, s ->
                    //判断是否显示首字符  由于上一行代码已经排序好了  index ==0 第一项显示字符
                    //s[0] != usersList[index - 1][0] 随后的字符串如果与上一个字符串的首字母 不一样 就显示
                    var showFirstLetter = index == 0 || s[0] != usersList[index - 1][0]
                    //添加数据
                    val contactListItem = ContactListItem(s, s[0].toUpperCase(), showFirstLetter)
                    contactListItems.add(contactListItem)
                    //保存联系人列表到数据库
                    val contact = Contact(mutableMapOf("name" to s))
                    IMDatabase.instance.saveContact(contact)
                }
                //通知view层更新界面
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