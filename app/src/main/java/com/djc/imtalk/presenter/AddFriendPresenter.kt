package com.djc.imtalk.presenter

import android.util.Log
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.BmobUser
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import com.djc.imtalk.contract.AddFriendContract
import com.djc.imtalk.data.AddFriendItem
import com.djc.imtalk.data.db.IMDatabase
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
                    val allContacts = IMDatabase.instance.getAllContacts()
                    doAsync {
                        p0?.forEach {

                            //比对是否已经添加过好友
                            var isAdded = false
                            for (contact in allContacts) {
                                Log.d("DJC", "数据库name=" + contact.name)
                                Log.d("DJC", "查询name=" + it.username)
                                if (contact.name == it.username) {
                                    isAdded = true
                                }
                            }
                            val addFriendItem = AddFriendItem(it.username, it.createdAt, isAdded)
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