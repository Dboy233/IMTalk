package com.djc.imtalk.ui.activity

import android.support.design.internal.BottomNavigationItemView
import android.support.design.internal.BottomNavigationMenuView
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.djc.imtalk.R
import com.djc.imtalk.adapter.EMMessageListenerAdapter
import com.djc.imtalk.factory.FragmentFactory
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMMessage
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.find

class MainActivity : BaseActivity() {
    override fun getLayoutResId(): Int = R.layout.activity_main
    private lateinit var menuView: BottomNavigationMenuView//bottom菜单
    private lateinit var itemView: BottomNavigationItemView//消息框
    private lateinit var badge: View//角标视图
    override fun init() {
        super.init()

        //初始界面加载消息界面
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_frame, FragmentFactory.instance.getFragment(R.id.tab_conversation))
            .commit()

        //选项卡切换
        bottom_bar.setOnNavigationItemSelectedListener { item ->
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_frame, FragmentFactory.instance.getFragment(item.itemId))
                .commit()
            true
        }
        //监听消息更新底部标签角标   后期整合
        EMClient.getInstance().chatManager().addMessageListener(messageListener)
        menuView = bottom_bar.getChildAt(0) as BottomNavigationMenuView
        itemView = menuView.getChildAt(0) as BottomNavigationItemView
        badge = LayoutInflater.from(this).inflate(R.layout.menu_badge, menuView, false)
        itemView.addView(badge)//添加角标
    }

    //消息监听器
    private val messageListener = object : EMMessageListenerAdapter() {
        override fun onMessageReceived(p0: MutableList<EMMessage>?) {
            runOnUiThread {
                updateBadgeTag() //刷新角标
            }
        }
    }

    override fun onResume() {
        super.onResume()
        //刷新角标
        updateBadgeTag()
    }


    //修改角标
    private fun updateBadgeTag() {
        //获取未读消息
        val unreadMessageCount = EMClient.getInstance().chatManager().unreadMessageCount
        if (unreadMessageCount > 0) {
            badge.find<TextView>(R.id.tv_msg_count).visibility = View.VISIBLE
            badge.find<TextView>(R.id.tv_msg_count).text = unreadMessageCount.toString()
        } else {
            badge.find<TextView>(R.id.tv_msg_count).visibility = View.GONE

        }

    }

    override fun onDestroy() {
        super.onDestroy()
        EMClient.getInstance().chatManager().removeMessageListener(messageListener)
    }
}
