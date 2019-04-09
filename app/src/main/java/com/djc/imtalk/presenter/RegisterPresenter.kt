package com.djc.imtalk.presenter

import cn.bmob.v3.BmobUser
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.SaveListener
import com.djc.imtalk.contract.RegisterContract
import com.djc.imtalk.extentions.isValidPassword
import com.djc.imtalk.extentions.isValidUserName


/**
 *@author ： created by dujiangchuan
 * 时间    ：2019/4/9 16
 * 邮箱    ：894230813@qq.com
 */
class RegisterPresenter(val view: RegisterContract.View) : RegisterContract.Presenter {
    override fun register(userName: String, password: String, confirmPassword: String) {
        //检查用户名
        if (userName.isValidUserName()) {
            //检查密码
            if (password.isValidPassword()) {
                //检查确认密码
                if (password == confirmPassword) {
                    //开始注册
                    view.onStartRegister()
                    //注册Module
                    registerBmob(userName, password)

                } else view.onConFirmPasswordError()
            } else view.onPasswordError()
        } else view.onUserNameError()

    }

    private fun registerBmob(userName: String, password: String) {
        val bu = BmobUser()
        bu.username = userName
        bu.setPassword(password)
//        bu.email = "sendi@163.com"
        //注意：不能用save方法进行注册
        bu.signUp(object : SaveListener<BmobUser>() {
            override fun done(s: BmobUser, e: BmobException?) {
                if (e == null) {
                    //注册成功
                    //注册到环信
                } else {
                    //注册失败
                    view.onRegisterFailed()
                }
            }
        })

    }
}