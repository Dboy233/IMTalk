package com.djc.imtalk

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.inputmethod.InputMethodManager

/**
 *@author ： created by dujiangchuan
 * 时间    ：2019/4/9 11
 * 邮箱    ：894230813@qq.com
 */
abstract class BaseActivity : AppCompatActivity() {

    val progressDialog by lazy {
        ProgressDialog(this)

    }

    val inputMismatchManager by lazy {
        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())
        init()
    }

    //初始化公共功能
    open fun init() {

    }


    //子类实现方法返回布局id
    abstract fun getLayoutResId(): Int

    /**
     * 显示加载框
     */
    fun showProgress(msg: String) {
        progressDialog.setMessage(msg)
        progressDialog.show()
    }

    /**
     * 关闭
     */
    fun dissProgress() {
        if (progressDialog.isShowing)
            progressDialog.dismiss()
    }

    /**
     * 隐藏软键盘
     */
    fun hideSoftKeyboard() {
        inputMismatchManager.hideSoftInputFromWindow(currentFocus.windowToken, 0)
    }
}