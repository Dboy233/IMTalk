package com.djc.imtalk.contract

import android.os.Handler
import android.os.Looper

/**
 *@author ： created by dujiangchuan
 * 时间    ：2019/4/9 13
 * 邮箱    ：894230813@qq.com
 */
interface BasePresenter {
    companion object {
        val handler by lazy {
            Handler(Looper.getMainLooper())
        }
    }

    fun uiThread(f: () -> Unit) {
        handler.post {
            f()
        }
    }

}