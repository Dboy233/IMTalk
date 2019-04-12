package com.djc.imtalk.app

import android.app.ActivityManager
import android.app.Application
import android.content.Context
import android.media.AudioManager
import android.media.SoundPool
import cn.bmob.v3.Bmob
import com.djc.imtalk.BuildConfig
import com.djc.imtalk.R
import com.djc.imtalk.adapter.EMMessageListenerAdapter
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMMessage
import com.hyphenate.chat.EMOptions


/**
 *@author ： created by dujiangchuan
 * 时间    ：2019/4/9 13
 * 邮箱    ：894230813@qq.com
 */
class IMApplication : Application() {
    companion object {
        lateinit var instance: IMApplication
    }

    val soundPool = SoundPool(2, AudioManager.STREAM_MUSIC, 0)
    val duan by lazy {
        soundPool.load(instance, R.raw.music_water, 0)

    }
    val chang by lazy {
        soundPool.load(instance, R.raw.music_water, 0)
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        //初始化
        val options = EMOptions()
        EMClient.getInstance().init(applicationContext, options)
        //在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        EMClient.getInstance().setDebugMode(BuildConfig.DEBUG);

        //bmob
        Bmob.initialize(applicationContext, "43e1e032a87b4e7db517887ba505248b")

        EMClient.getInstance().chatManager().addMessageListener(messageListAdapter)
    }

    private val messageListAdapter = object : EMMessageListenerAdapter() {
        override fun onMessageReceived(p0: MutableList<EMMessage>?) {
            //如果在前台播放段的声音
            if (isForeground()) {
                soundPool.play(duan, 1f, 1f, 0, 0, 1f)
            } else {
                //如果在后台播放长声音
                soundPool.play(duan, 1f, 1f, 0, 0, 1f)
            }


        }
    }

    private fun isForeground(): Boolean {
        val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        for (runningAppProcess in activityManager.runningAppProcesses) {
            if (runningAppProcess.processName == packageName) {
                //找到了app进程
                return runningAppProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND
            }
        }
        return false
    }

}