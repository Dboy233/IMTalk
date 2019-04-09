package com.djc.imtalk

import android.os.Handler
import com.djc.imtalk.contract.SplashContract
import com.djc.imtalk.presenter.SplashPresenter
import org.jetbrains.anko.startActivity

/**
 *@author ： created by dujiangchuan
 * 时间    ：2019/4/9 11
 * 邮箱    ：894230813@qq.com
 */
class SplashActivity : BaseActivity(), SplashContract.View {
    val presenter = SplashPresenter(this)

    companion object {
        val DELAY = 2000L
    }

    val handler by lazy {
        Handler()
    }

    override fun init() {
        super.init()
        presenter.checkLoginStatus()
    }

    override fun getLayoutResId(): Int = R.layout.activity_splash
    //延时两秒 跳转到登陆界面
    override fun onNotLoggedIn() {
        handler.postDelayed({
            startActivity<LoginActivity>()//跳转到登陆界面
            finish()
        }, DELAY)


    }

    //跳转到主界面
    override fun onLoggedIn() {
        startActivity<MainActivity>()
    }

}