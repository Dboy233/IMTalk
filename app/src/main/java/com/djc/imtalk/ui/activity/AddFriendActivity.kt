package com.djc.imtalk.ui.activity

import com.djc.imtalk.R
import kotlinx.android.synthetic.main.header.*

/**
 *@author ： created by dujiangchuan
 * 时间    ：2019/4/11 14
 * 邮箱    ：894230813@qq.com
 */
class AddFriendActivity : BaseActivity() {
    override fun getLayoutResId(): Int = R.layout.acticity_add_friend
    override fun init() {
        super.init()
        header_title.text = getString(R.string.add_friend)
    }

}