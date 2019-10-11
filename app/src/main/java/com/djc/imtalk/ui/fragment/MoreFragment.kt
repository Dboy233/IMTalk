package com.djc.imtalk.ui.fragment

import android.view.View
import com.djc.imtalk.R
import com.djc.imtalk.adapter.EMCallBackAdapter
import com.djc.imtalk.ui.activity.LoginActivity
import com.hyphenate.chat.EMClient
import kotlinx.android.synthetic.main.fragment_more.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.runOnUiThread
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.toast

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
        val logoutString =
            String.format(getString(R.string.logout), EMClient.getInstance().currentUser)
        bt_logout.text = logoutString

        //退登录功能
        bt_logout.setOnClickListener { logout() }

        //设置头像
        item_change_header.setOnItemViewClickListener(View.OnClickListener {
            toast("开发中").show()
        })
    }

    private fun logout() {

        EMClient.getInstance().logout(true, object : EMCallBackAdapter() {
            override fun onSuccess() {
                //回到登录界面
                context!!.runOnUiThread { toast(getString(R.string.logout_success)) }
                startActivity<LoginActivity>()
                activity!!.finish()
            }

            override fun onError(p0: Int, p1: String?) {
                //提醒推出失败
                context!!.runOnUiThread { toast(getString(R.string.logout_failed)) }
            }
        })

    }
}