package com.djc.imtalk

import com.djc.imtalk.contract.LoginContract
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 *@author ： created by dujiangchuan
 * 时间    ：2019/4/9 13
 * 邮箱    ：894230813@qq.com
 */
class LoginActivity : BaseActivity(), LoginContract.View {

    override fun getLayoutResId(): Int = R.layout.activity_login

    override fun onUserNameError() {
        ed_userName.error = getString(R.string.user_name_error)

    }

    override fun onPasswordError() {
        ed_password.error = getString(R.string.password_error)
    }

    //弹出一个进度条
    override fun onStartLogin() {
        showProgress(getString(R.string.logging))
    }

    override fun onLoggedSuccess() {
        //隐藏进度条
        dissProgress()
        //跳转主界面
        startActivity<MainActivity>()
        //推出登录界面
        finish()
    }

    override fun onLoggedFailed() {
        //隐藏进度条
        dissProgress()
        //弹出toast
        toast(R.string.loggin_failed)

    }


}