package com.djc.imtalk.app

import android.app.Application
import cn.bmob.v3.Bmob
import com.djc.imtalk.BuildConfig
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMOptions


/**
 *@author ： created by dujiangchuan
 * 时间    ：2019/4/9 13
 * 邮箱    ：894230813@qq.com
 */
class IMApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        //初始化
        val options = EMOptions()
        EMClient.getInstance().init(applicationContext, options)
        //在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        EMClient.getInstance().setDebugMode(BuildConfig.DEBUG);

        //bmob
        Bmob.initialize(applicationContext, "43e1e032a87b4e7db517887ba505248b")


    }
}