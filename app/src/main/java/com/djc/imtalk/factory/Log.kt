package com.djc.openweather.Utils

import android.util.Log

/**
 *@author ： created by dujiangchuan
 * 时间    ：2019/4/17 21
 * 邮箱    ：894230813@qq.com
 */
interface Log {
    companion object {
        var TAG: String = "TAG"
        var isDbug: Boolean = true
    }

    fun log(msg: String) {
        if (isDbug)
            Log.d(TAG, "-------->$msg")
    }

    fun log(msg: Int) {
        if (isDbug)
            Log.d(TAG, "-------->$msg")
    }

    fun log(msg: Boolean) {
        if (isDbug)
            Log.d(TAG, "-------->$msg")
    }
}
