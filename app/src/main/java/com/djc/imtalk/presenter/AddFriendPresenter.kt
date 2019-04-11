package com.djc.imtalk.presenter

import cn.bmob.v3.BmobQuery
import cn.bmob.v3.BmobUser
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import com.djc.imtalk.contract.AddFriendContract
import com.hyphenate.chat.EMClient

/**
 *@author ： created by dujiangchuan
 * 时间    ：2019/4/11 14
 * 邮箱    ：894230813@qq.com
 */
class AddFriendPresenter(val view: AddFriendContract.View) : AddFriendContract.Presenter {


    override fun search(key: String) {
        //从Bmob云查询好友
        val query = BmobQuery<BmobUser>()
        query.addWhereContains("username", key)
            .addWhereNotEqualTo("username", EMClient.getInstance().currentUser)
        query.findObjects(object : FindListener<BmobUser>() {
            override fun done(p0: MutableList<BmobUser>?, p1: BmobException?) {
                if (p1 == null)  view.onSearchSuccess()
                else view.onSearchFailed()
            }
        })
    }


}