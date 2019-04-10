package com.djc.imtalk.ui.fragment

import com.djc.imtalk.R
import com.hyphenate.chat.EMClient
import kotlinx.android.synthetic.main.fragment_more.*
import kotlinx.android.synthetic.main.header.*

/**
 *@author ： created by dujiangchuan
 * 时间    ：2019/4/9 19
 * 邮箱    ：894230813@qq.com
 */
class MoreFragment : BaseFragment() {
    override fun getLayoutResId(): Int = R.layout.fragment_more

    override fun init() {
        super.init()
        header_title.text = getString(R.string.tab_more_string)
        //登录按钮文字格式化
        val logoutString = String.format(getString(R.string.logout), EMClient.getInstance().currentUser)
        bt_logout.text = logoutString
    }
}