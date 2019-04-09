package com.djc.imtalk.contract

/**
 *@author ： created by dujiangchuan
 * 时间    ：2019/4/9 14
 * 邮箱    ：894230813@qq.com
 */
interface LoginContract {
    interface Presenter : BasePresenter {
        fun login(userName:String,password:String)
    }

    interface View {
        fun onUserNameError()
        fun onPasswordError()
        fun onStartLogin()
        fun onLoggedSuccess()
        fun onLoggedFailed()
    }
}