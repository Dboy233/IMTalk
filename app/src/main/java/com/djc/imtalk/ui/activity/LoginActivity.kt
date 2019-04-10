package com.djc.imtalk.ui.activity

import android.Manifest
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import com.djc.imtalk.R
import com.djc.imtalk.contract.LoginContract
import com.djc.imtalk.presenter.LoginPresenter
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 *@author ： created by dujiangchuan
 * 时间    ：2019/4/9 13
 * 邮箱    ：894230813@qq.com
 */
class LoginActivity : BaseActivity(), LoginContract.View {

    private val presenter = LoginPresenter(this)

    override fun getLayoutResId(): Int = R.layout.activity_login


    override fun init() {
        super.init()
        tv_newUser.setOnClickListener {
            startActivity<RegisterActivity>()
            finish()
        }
        bt_login.setOnClickListener {
            login()
        }
        ed_password.setOnEditorActionListener { _, _, _ ->
            login()
            true
        }
    }

    //登录按钮 登录
    private fun login() {
        //隐藏键盘
        hideSoftKeyboard()
        if (hasWriteExternalStoragePermission()) {
            val userNameString = ed_userName.text.trim().toString()
            val passwordString = ed_password.text.trim().toString()
            presenter.login(userNameString, passwordString)
        } else {
            applyWriteExternalStoragePermission()
        }


    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            //同意权限,开始登录
            login()
        } else toast("权限被拒绝了")
    }

    //申请权限
    private fun applyWriteExternalStoragePermission() {
        val permission = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        ActivityCompat.requestPermissions(this, permission, 0)

    }

    //检查权限
    private fun hasWriteExternalStoragePermission(): Boolean {

        val checkSelfPermission = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        return checkSelfPermission == PackageManager.PERMISSION_GRANTED
    }

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