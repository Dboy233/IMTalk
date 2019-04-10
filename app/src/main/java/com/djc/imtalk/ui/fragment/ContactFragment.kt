package com.djc.imtalk.ui.fragment

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.djc.imtalk.R
import com.djc.imtalk.adapter.ContactListAdapter
import kotlinx.android.synthetic.main.fragment_contact.*
import kotlinx.android.synthetic.main.header.*

/**
 *@author ： created by dujiangchuan
 * 时间    ：2019/4/9 19
 * 邮箱    ：894230813@qq.com
 */
class ContactFragment : BaseFragment() {
    override fun getLayoutResId(): Int = R.layout.fragment_contact
    override fun init() {
        super.init()
        header_title.text = getString(R.string.tab_contact_string)
        add.visibility = View.VISIBLE
        //下拉刷新状态
        swipeRefreshLayout.apply {
            setColorSchemeResources(R.color.blue_light)
            isRefreshing = true
        }
        recyclerView_contact.apply {

            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = ContactListAdapter(context)
        }
    }
}