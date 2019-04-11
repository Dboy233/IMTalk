package com.djc.imtalk.presenter

import android.util.Log
import com.djc.imtalk.adapter.EMCallBackAdapter
import com.djc.imtalk.contract.LoginContract
import com.djc.imtalk.extentions.isValidPassword
import com.djc.imtalk.extentions.isValidUserName
import com.hyphenate.chat.EMClient

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
                loginBmob(userName,password)//要先查询数据库是否有这个用户
                logEaseMob(userName, password)//登录到环信服务器

            } else view.onPasswordError()
        } else view.onUserNameError()

    }

    private fun loginBmob(userName: String, password: String) {


    }

    private fun logEaseMob(userName: String, password: String) {

        EMClient.getInstance().login(userName, password, object : EMCallBackAdapter() {
            //在子线程中
            override fun onSuccess() {
                EMClient.getInstance().groupManager().loadAllGroups()
                EMClient.getInstance().chatManager().loadAllConversations()
                Log.d("main", "登录聊天服务器成功！")
                //在主线程中通知View层
                uiThread { view.onLoggedSuccess() }
            }

            override fun onError(p0: Int, p1: String?) {
                uiThread { view.onLoggedFailed() }
            }
        })
    }

}