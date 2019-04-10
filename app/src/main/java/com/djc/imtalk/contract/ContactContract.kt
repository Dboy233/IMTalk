package com.djc.imtalk.contract

/**
 *@author ： created by dujiangchuan
 * 时间    ：2019/4/10 19
 * 邮箱    ：894230813@qq.com
 */
interface ContactContract {
    interface Presenter : BasePresenter {
        fun loadContacts()
    }

    interface View {
        fun onLoadContactSuccess()
        fun onLoadContactFailed()
    }
}