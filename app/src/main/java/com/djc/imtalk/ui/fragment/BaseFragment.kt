package com.djc.imtalk.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.djc.openweather.Utils.Log

/**
 *@author ： created by dujiangchuan
 * 时间    ：2019/4/9 11
 * 邮箱    ：894230813@qq.com
 */
abstract class BaseFragment : Fragment(), Log {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(getLayoutResId(), null)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) =
        init()


    open fun init() {}


    abstract fun getLayoutResId(): Int


}