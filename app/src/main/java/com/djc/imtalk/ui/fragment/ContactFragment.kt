package com.djc.imtalk.ui.fragment

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.djc.imtalk.R
import com.djc.imtalk.adapter.ContactListAdapter
import com.djc.imtalk.adapter.EMContactListenerAdapter
import com.djc.imtalk.contract.ContactContract
import com.djc.imtalk.presenter.ContactPresenter
import com.djc.imtalk.widget.SlideBar
import com.hyphenate.chat.EMClient
import kotlinx.android.synthetic.main.fragment_contact.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.toast

/**
 *@author ： created by dujiangchuan
 * 时间    ：2019/4/9 19
 * 邮箱    ：894230813@qq.com
 */
class ContactFragment : BaseFragment(), ContactContract.View {
    val presenter = ContactPresenter(this)
    override fun getLayoutResId(): Int = R.layout.fragment_contact
    override fun init() {
        super.init()
        header_title.text = getString(R.string.tab_contact_string)
        add.visibility = View.VISIBLE
        //下拉刷新状态
        swipeRefreshLayout.apply {
            setColorSchemeResources(R.color.blue_light)
            isRefreshing = true
            setOnRefreshListener { presenter.loadContacts() }
        }
        //初始化列表
        recyclerView_contact.apply {

            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            //绑定监听器
            adapter = ContactListAdapter(context, presenter.contactListItems)
        }
        //好友信息监听器
        EMClient.getInstance().contactManager().setContactListener(object : EMContactListenerAdapter() {
            //好友删除
            override fun onContactDeleted(p0: String?) {
                //重新获取联系人列表
                presenter.loadContacts()
            }
        })
        presenter.loadContacts()
        //设置slider监听器
        slidebar.onSectionChangeListener = object : SlideBar.OnSectionChangeListener {
            override fun onSectionChange(firstLetter: String) {
                tv_section.visibility = View.VISIBLE
                tv_section.text = firstLetter
            }

            override fun onSlideFinish() {
                tv_section.visibility = View.GONE
            }
        }
    }

    override fun onLoadContactSuccess() {
        swipeRefreshLayout.isRefreshing = false
        recyclerView_contact.adapter!!.notifyDataSetChanged()

    }

    override fun onLoadContactFailed() {
        swipeRefreshLayout.isRefreshing = false
        context!!.toast(getString(R.string.load_fialed))
    }

}
