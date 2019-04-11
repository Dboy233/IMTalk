package com.djc.imtalk.ui.activity

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.djc.imtalk.R
import com.djc.imtalk.adapter.EMMessageListenerAdapter
import com.djc.imtalk.adapter.MessageListAdapter
import com.djc.imtalk.contract.ChatContract
import com.djc.imtalk.presenter.ChatPresenter
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMMessage
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
        //设置消息接收监听器
        EMClient.getInstance().chatManager().addMessageListener(messageListener)
        presenter.loadMessages(username)
    }

    private fun initRecyclerView() {
        recyclerView_chat.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = MessageListAdapter(context, presenter.messages)
            //监听recyclerView的滑动
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    //当recycleView是一个空闲的状态，检查是否滑倒顶部，要加载更多数据库
                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                        //如果第一个可见条目的位置是0，为滑倒了顶部
                        val linearLayoutManager = layoutManager as LinearLayoutManager
                        if (linearLayoutManager.findFirstVisibleItemPosition() == 0) {
                            //加载数据
                            presenter.loadMoreMessages(username)

                        }
                    }
                }
            })
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

    //消息发送成功
    override fun onSendMessageSuccess() {
        recyclerView_chat.adapter?.notifyDataSetChanged()
        toast("发送成功")
        ed_sent.text.clear()
        //接收到消息后列表移动到最后
        scrollToBottom()
    }

    private fun scrollToBottom() {
//        recyclerView_chat.scrollToPosition(presenter.messages.size - 1)
        recyclerView_chat.smoothScrollToPosition(presenter.messages.size - 1)// 平滑移动

    }

    //消息发送失败
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

    private val messageListener = object : EMMessageListenerAdapter() {
        override fun onMessageReceived(p0: MutableList<EMMessage>?) {
            presenter.addMessage(username, p0)
            runOnUiThread {
                recyclerView_chat.adapter?.notifyDataSetChanged()
                scrollToBottom()//接收到消息后移动到最新的消息位置
            }

        }
    }

    //加载完聊天记录
    override fun onMessageLoaded() {
        recyclerView_chat.adapter?.notifyDataSetChanged()
        scrollToBottom()

    }

    //加载10条聊天记录
    override fun onMoreMessageLoaded(size: Int) {
        recyclerView_chat.adapter?.notifyDataSetChanged()
        recyclerView_chat.smoothScrollToPosition(size)
    }

    override fun onDestroy() {
        super.onDestroy()
        EMClient.getInstance().chatManager().removeMessageListener(messageListener)
    }
}