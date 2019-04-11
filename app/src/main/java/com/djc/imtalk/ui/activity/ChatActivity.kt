package com.djc.imtalk.ui.activity

import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.djc.imtalk.R
import com.djc.imtalk.adapter.MessageListAdapter
import com.djc.imtalk.contract.ChatContract
import com.djc.imtalk.presenter.ChatPresenter
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.toast

/**
 *@author ： created by dujiangchuan
 * 时间    ：2019/4/10 20
 * 邮箱    ：894230813@qq.com
 */
class ChatActivity : BaseActivity(), ChatContract.View {
    val presenter = ChatPresenter(this)
    lateinit var username: String
    override fun init() {
        initHead()
        initEditText()
        initRecyclerView()

    }

    private fun initRecyclerView() {
        recyclerView_chat.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = MessageListAdapter(context, presenter.messages)
        }

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
        btn_sent.setOnClickListener {
            send()
        }
        ed_sent.setOnEditorActionListener { _, _, _ ->
            send()
            true
        }
    }

    //发送信息
    private fun send() {
        hideSoftKeyboard()
        val messageString = ed_sent.text.toString()
        presenter.sendMessage(username, messageString)

    }

    override fun onStartSendMessage() {
        //通知recyclerview刷新列表
        recyclerView_chat.adapter?.notifyDataSetChanged()
    }

    override fun onSendMessageSuccess() {
        recyclerView_chat.adapter?.notifyDataSetChanged()
        toast("发送成功")
        ed_sent.text.clear()
    }

    override fun onSendMessageFailed() {
        toast("发送失败")
        recyclerView_chat.adapter?.notifyDataSetChanged()
    }

    private fun initHead() {
        back.visibility = View.VISIBLE
        back.setOnClickListener { finish() }

        //获取聊天的用户
        username = intent.getStringExtra("userName")
        val titleString = String.format("%s", username)
        header_title.text = username


    }

    override fun getLayoutResId(): Int = R.layout.activity_chat
}