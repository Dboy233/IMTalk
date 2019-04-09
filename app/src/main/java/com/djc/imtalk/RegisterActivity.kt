package com.djc.imtalk

import com.djc.imtalk.contract.RegisterContract
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 *@author ： created by dujiangchuan
 * 时间    ：2019/4/9 16
 * 邮箱    ：894230813@qq.com
 */
class RegisterActivity : BaseActivity(), RegisterContract.View {
    override fun init() {
        //已有账户去登陆
        tv_hisUser.setOnClickListener {
            startActivity<LoginActivity>()
            finish()
        }
    }

    override fun onUserNameError() {
        ed_userName.error = getString(R.string.user_name_error)
    }

    override fun onPasswordError() {
        ed_password.error = getString(R.string.password_error)
    }

    override fun onConFirmPasswordError() {
        ed_confirm_password.error = getString(R.string.confirm_password_error)
    }

    override fun onStartRegister() {
        showProgress(getString(R.string.registing))
    }

    //注册成功 跳转到登录界面
    override fun onRegisterSuccess() {
        dissProgress()
        startActivity<LoginActivity>()
        finish()
    }

    //    注册失败
    override fun onRegisterFailed() {
        dissProgress()
        toast(getString(R.string.register_error))
    }

    override fun getLayoutResId(): Int = R.layout.activity_register
}