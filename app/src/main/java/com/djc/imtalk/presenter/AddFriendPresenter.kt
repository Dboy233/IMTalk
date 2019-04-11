package com.djc.imtalk.presenter

import cn.bmob.v3.BmobQuery
import cn.bmob.v3.BmobUser
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import com.djc.imtalk.contract.AddFriendContract
import com.djc.imtalk.data.AddFriendItem
import com.hyphenate.chat.EMClient
import org.jetbrains.anko.doAsync

/**
 *@author ： created by dujiangchuan
 * 时间    ：2019/4/11 14
 * 邮箱    ：894230813@qq.com
 */
class AddFriendPresenter(val view: AddFriendContract.View) : AddFriendContract.Presenter {


    val addFriendItems = mutableListOf<AddFriendItem>()
    override fun search(key: String) {
        addFriendItems.clear()
        //从Bmob云查询好友
        val query = BmobQuery<BmobUser>()
        //查询信息列队
        query.addWhereContains("username", key)
            .addWhereNotEqualTo("username", EMClient.getInstance().currentUser)
        //查询回调接口
        query.findObjects(object : FindListener<BmobUser>() {
            override fun done(p0: MutableList<BmobUser>?, p1: BmobException?) {
                if (p1 == null) {
                    //数据处理
                    doAsync {
                        p0?.forEach {
                            val addFriendItem = AddFriendItem(it.username, it.createdAt)
                            addFriendItems.add(addFriendItem)
                        }
                        //通知ui刷新
                        uiThread { view.onSearchSuccess() }
                    }
                } else view.onSearchFailed()
            }
        })
    }


}