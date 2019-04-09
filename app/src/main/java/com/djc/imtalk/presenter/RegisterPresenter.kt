package com.djc.imtalk.presenter

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


                } else view.onConFirmPasswordError()
            } else view.onPasswordError()
        } else view.onUserNameError()

    }
}