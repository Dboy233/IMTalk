package com.djc.imtalk.presenter

import com.djc.imtalk.contract.SplashContract
import com.hyphenate.chat.EMClient

/**
 *@author ： created by dujiangchuan
 * 时间    ：2019/4/9 13
 * 邮箱    ：894230813@qq.com
 */
class SplashPresenter(val view: SplashContract.View) : SplashContract.Presenter {
    override fun checkLoginStatus() {
        if (isLoggedIn()) view.onLoggedIn()
        else view.onNotLoggedIn()
    }

    //是否登录到环信服务器
    private fun isLoggedIn(): Boolean =
        EMClient.getInstance().isConnected && EMClient.getInstance().isLoggedInBefore

}