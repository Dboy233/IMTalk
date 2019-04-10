package com.djc.imtalk.presenter

import com.djc.imtalk.contract.ContactContract
import com.hyphenate.chat.EMClient
import com.hyphenate.exceptions.HyphenateException
import org.jetbrains.anko.doAsync

/**
 *@author ： created by dujiangchuan
 * 时间    ：2019/4/10 19
 * 邮箱    ：894230813@qq.com
 */
class ContactPresenter(val view: ContactContract.View) : ContactContract.Presenter {

    override fun loadContacts() {
        doAsync {
            try {
                val usersList = EMClient.getInstance().contactManager().allContactsFromServer
                uiThread { view.onLoadContactSuccess() }
            } catch (e: HyphenateException) {
                uiThread {
                    view.onLoadContactFailed()
                }
            }

        }

    }
}