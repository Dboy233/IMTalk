package com.djc.imtalk.ui.activity

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.djc.imtalk.R
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.android.synthetic.main.header.*

/**
 *@author ： created by dujiangchuan
 * 时间    ：2019/4/10 20
 * 邮箱    ：894230813@qq.com
 */
class ChatActivity : BaseActivity() {


    override fun init() {
        initHead()
        initEditText()
    }

    //
    private fun initEditText() {
        ed_sent.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                //输入框有文字时 可以发送
                btn_sent.isEnabled = !s.isNullOrEmpty()
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

    }

    private fun initHead() {
        back.visibility = View.VISIBLE
        back.setOnClickListener { finish() }

        //获取聊天的用户
        val username = intent.getStringExtra("userName")
        val titleString = String.format("%s", username)
        header_title.text = username


    }

    override fun getLayoutResId(): Int = R.layout.activity_chat
}