package com.djc.imtalk.ui.activity

import com.djc.imtalk.R
import com.djc.imtalk.contract.RegisterContract
import com.djc.imtalk.presenter.RegisterPresenter
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 *@author ： created by dujiangchuan
 * 时间    ：2019/4/9 16
 * 邮箱    ：894230813@qq.com
 */
class RegisterActivity : BaseActivity(), RegisterContract.View {
    private val presenter = RegisterPresenter(this)
    override fun init() {
        //已有账户去登陆
        tv_hisUser.setOnClickListener {
            startActivity<LoginActivity>()
            finish()
        }

        bt_register.setOnClickListener { register() }
        ed_confirm_password.setOnEditorActionListener { _, _, _ ->
            register()
            true
        }
    }

    private fun register() {
        //隐藏软键盘
        hideSoftKeyboard()

        val userNameString = ed_userName.text.trim().toString()
        val passwordString = ed_password.text.trim().toString()
        val confirmPasswordString = ed_confirm_password.text.trim().toString()
        presenter.register(userNameString, passwordString, confirmPasswordString)

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
        toast("注册成功")
        dissMissProgress()
        startActivity<LoginActivity>()
        finish()
    }

    //    注册失败
    override fun onRegisterFailed() {
        dissMissProgress()
        toast(getString(R.string.register_error))
    }

    override fun onUserExist() {
        dissMissProgress()
        toast(getString(R.string.user_exist))
    }

    override fun getLayoutResId(): Int = R.layout.activity_register
}