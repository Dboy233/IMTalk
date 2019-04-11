package com.djc.imtalk.ui.fragment

import android.support.v7.widget.LinearLayoutManager
import com.djc.imtalk.R
import com.djc.imtalk.adapter.ConversationListAdapter
import kotlinx.android.synthetic.main.fragment_conversation.*
import kotlinx.android.synthetic.main.header.*

/**
 *@author ： created by dujiangchuan
 * 时间    ：2019/4/9 19
 * 邮箱    ：894230813@qq.com
 */
class ConversationFragment : BaseFragment() {
    override fun getLayoutResId(): Int = R.layout.fragment_conversation


    override fun init() {
        super.init()
        header_title.text = getString(R.string.message)

        recyclerView_conversation.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = ConversationListAdapter(context)
        }

    }
}