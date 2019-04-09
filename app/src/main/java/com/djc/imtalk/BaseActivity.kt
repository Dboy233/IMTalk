package com.djc.imtalk

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 *@author ： created by dujiangchuan
 * 时间    ：2019/4/9 11
 * 邮箱    ：894230813@qq.com
 */
abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())
        init()
    }

    //初始化公共功能
    fun init() {

    }


    //子类实现方法返回布局id
    abstract fun getLayoutResId(): Int
}