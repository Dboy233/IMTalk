package com.djc.imtalk.presenter

import com.djc.imtalk.contract.LoginContract
import com.djc.imtalk.extentions.isValidPassword
import com.djc.imtalk.extentions.isValidUserName

/**
 *@author ： created by dujiangchuan
 * 时间    ：2019/4/9 14
 * 邮箱    ：894230813@qq.com
 */
class LoginPresenter(val view: LoginContract.View) : LoginContract.Presenter {

    override fun login(userName: String, password: String) {
        if (userName.isValidUserName()) {
            //用户名合法
            if (password.isValidPassword()) {
                //密码合法
                //开始登录
                view.onStartLogin()
                logEaseMob(userName,password)//登录到环信服务器

            } else view.onPasswordError()
        } else view.onPasswordError()

    }

    private fun logEaseMob(userName: String, password: String) {


    }

}