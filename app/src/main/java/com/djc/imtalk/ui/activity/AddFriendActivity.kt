package com.djc.imtalk.ui.activity

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.djc.imtalk.R
import com.djc.imtalk.adapter.AddFriendListAdapter
import com.djc.imtalk.contract.AddFriendContract
import com.djc.imtalk.presenter.AddFriendPresenter
import kotlinx.android.synthetic.main.acticity_add_friend.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.toast

/**
 *@author ： created by dujiangchuan
 * 时间    ：2019/4/11 14
 * 邮箱    ：894230813@qq.com
 */
class AddFriendActivity : BaseActivity(), AddFriendContract.View {

    val presenter = AddFriendPresenter(this)
    override fun getLayoutResId(): Int = R.layout.acticity_add_friend

    override fun init() {
        super.init()
        header_title.text = getString(R.string.add_friend)
        back.visibility = View.VISIBLE
        back.setOnClickListener { finish() }
        recyclerView_add.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = AddFriendListAdapter(context, presenter.addFriendItems)
        }


        img_search.setOnClickListener { search() }
        ed_userName.setOnEditorActionListener { _, _, _ ->
            search()
            true
        }
    }

    private fun search() {
        hideSoftKeyboard()
        showProgress(getString(R.string.searching_now))
        val key = ed_userName.text.trim().toString()
        presenter.search(key)
    }

    override fun onSearchSuccess() {
        dissMissProgress()
        toast("搜索成功")
        recyclerView_add.adapter?.notifyDataSetChanged()
    }

    override fun onSearchFailed() {
        dissMissProgress()
        toast("搜索失败")
    }

}
