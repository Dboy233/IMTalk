package com.djc.imtalk.contract

/**
 *@author ： created by dujiangchuan
 * 时间    ：2019/4/9 16
 * 邮箱    ：894230813@qq.com
 */
interface RegisterContract {
    interface Presenter : BasePresenter {
        fun register(userName: String, password: String, confirmPassword: String)
    }

    interface View {
        fun onUserNameError()
        fun onPasswordError()
        fun onConFirmPasswordError()
        fun onStartRegister()
        fun onRegisterSuccess()
        fun onRegisterFailed()
        fun onUserExist()
    }
}