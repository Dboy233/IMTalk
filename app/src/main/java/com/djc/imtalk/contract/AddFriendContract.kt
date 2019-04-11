package com.djc.imtalk.contract

/**
 *@author ： created by dujiangchuan
 * 时间    ：2019/4/11 14
 * 邮箱    ：894230813@qq.com
 */
interface AddFriendContract {
    interface Presenter : BasePresenter {
        fun search(key: String)

    }

    interface View {
        fun onSearchSuccess()
        fun onSearchFailed()
    }
}