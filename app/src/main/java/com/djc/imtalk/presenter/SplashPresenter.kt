package com.djc.imtalk.presenter

import com.djc.imtalk.contract.SplashContract

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

    private fun isLoggedIn(): Boolean {
        return false
    }
}