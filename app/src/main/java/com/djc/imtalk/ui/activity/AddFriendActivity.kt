package com.djc.imtalk.ui.activity

import android.support.v7.widget.LinearLayoutManager
import com.djc.imtalk.R
import com.djc.imtalk.adapter.AddFriendListAdapter
import com.djc.imtalk.contract.AddFriendContract
import kotlinx.android.synthetic.main.acticity_add_friend.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.toast

/**
 *@author ： created by dujiangchuan
 * 时间    ：2019/4/11 14
 * 邮箱    ：894230813@qq.com
 */
class AddFriendActivity : BaseActivity(), AddFriendContract.View {
    override fun getLayoutResId(): Int = R.layout.acticity_add_friend

    override fun init() {
        super.init()
        header_title.text = getString(R.string.add_friend)

        recyclerView_add.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = AddFriendListAdapter(context)
        }

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
