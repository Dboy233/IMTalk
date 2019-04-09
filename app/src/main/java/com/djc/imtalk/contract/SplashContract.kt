package com.djc.imtalk.contract

/**
 *@author ： created by dujiangchuan
 * 时间    ：2019/4/9 13
 * 邮箱    ：894230813@qq.com
 */
interface SplashContract {

    interface Presenter : BasePresenter {
        fun checkLoginStatus()//登录状态
    }

    interface View {
        fun onNotLoggedIn()
        fun onLoggedIn()
    }
}