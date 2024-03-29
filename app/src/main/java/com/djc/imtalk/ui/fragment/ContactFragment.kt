package com.djc.imtalk.ui.fragment

import android.animation.ObjectAnimator
import android.content.Context
import android.os.Handler
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.view.animation.LinearInterpolator
import com.djc.imtalk.R
import com.djc.imtalk.adapter.ContactListAdapter
import com.djc.imtalk.adapter.EMContactListenerAdapter
import com.djc.imtalk.contract.ContactContract
import com.djc.imtalk.presenter.ContactPresenter
import com.djc.imtalk.ui.activity.AddFriendActivity
import com.djc.imtalk.widget.SlideBar
import com.hyphenate.chat.EMClient
import kotlinx.android.synthetic.main.fragment_contact.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 *@author ： created by dujiangchuan
 * 时间    ：2019/4/9 19
 * 邮箱    ：894230813@qq.com
 */
class ContactFragment : BaseFragment(), ContactContract.View {
    val presenter = ContactPresenter(this)
    override fun getLayoutResId(): Int = R.layout.fragment_contact
    //初始化
    override fun init() {
        super.init()
        //初始化头部显示和添加按钮
        initAdd()
        //下拉刷新
        initSwipeRefreshLayout()
        //初始化列表
        initRecyclerView()
        //好友信息监听器
        initFriendListener()
        //获取联系人列表
        presenter.loadContacts()
        //设置slider监听器
        initSlider()

    }

    //好友信息监听器的设置
    private fun initFriendListener() {
        EMClient.getInstance().contactManager().setContactListener(contactListener)
    }

    //好友信息监听器
    private val contactListener = object : EMContactListenerAdapter() {
        //好友删除
        override fun onContactDeleted(p0: String?) {
            //重新获取联系人列表
            presenter.loadContacts()
        }

        //有好友添加
        override fun onContactAdded(p0: String?) {
            //重新获取联系人列表
            presenter.loadContacts()
        }
    }

    var isShowSlideBar: Boolean = true


    //侧滑选择器
    private fun initSlider() {

        show_slideBar_view.setOnClickListener {
            if (isShowSlideBar) {
                hideAnim()
            } else {
                showAnim()
            }
        }

        slidebar.onSectionChangeListener = object : SlideBar.OnSectionChangeListener {
            override fun onSectionChange(firstLetter: String) {
                tv_section.visibility = View.VISIBLE
                tv_section.text = firstLetter
                //移动列表
                try {
                    recyclerView_contact?.smoothScrollToPosition(getPosition(firstLetter))
                } catch (e: IllegalArgumentException) {
                    Log.e("RecyclerView", e.message)
                }
            }

            override fun onSlideFinish() {
                tv_section.visibility = View.GONE
            }
        }

        Handler().postDelayed({
            hideAnim()
        }, 800)
    }

    //recyclerView的初始化
    private fun initRecyclerView() {
        recyclerView_contact?.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            //绑定监听器
            adapter = ContactListAdapter(context, presenter.contactListItems)
        }
    }

    //下拉刷新功能
    private fun initSwipeRefreshLayout() {
        swipeRefreshLayout.apply {
            setColorSchemeResources(R.color.blue_light)
            isRefreshing = true
            setOnRefreshListener { presenter.loadContacts() }
        }
    }

    //头部功能
    private fun initAdd() {
        header_title.text = getString(R.string.tab_contact_string)
        add.visibility = View.VISIBLE
        add.setOnClickListener { context?.startActivity<AddFriendActivity>() }
    }

    //通过二分法得到集合加标
    private fun getPosition(firstLetter: String): Int =
        presenter.contactListItems.binarySearch { contactListItem ->
            contactListItem.firstLetter.minus(firstLetter[0])
        }


    override fun onLoadContactSuccess() {
        swipeRefreshLayout?.isRefreshing = false
        recyclerView_contact?.adapter?.notifyDataSetChanged()

    }

    override fun onLoadContactFailed() {
        swipeRefreshLayout?.isRefreshing = false
        context!!.toast(getString(R.string.load_fialed))
    }

    override fun onDestroy() {
        super.onDestroy()
        //移除好友信息监听器
        EMClient.getInstance().contactManager().removeContactListener(contactListener)
    }

    fun showAnim() {
        val showAnim: ObjectAnimator = ObjectAnimator.ofFloat(
            slidebar,
            "translationX",
            slidebar.layoutParams.width.toFloat(),
            0.0f
        )
        showAnim.apply {
            duration = 500
            interpolator = LinearInterpolator()
            start()
        }
        isShowSlideBar = true
    }

    fun hideAnim() {
        val hide: ObjectAnimator = ObjectAnimator.ofFloat(
            slidebar,
            "translationX",
            0.0f,
            slidebar.layoutParams.width.toFloat()
        )
        hide.apply {
            duration = 500
            interpolator = LinearInterpolator()
            start()
        }
        isShowSlideBar = false
    }


    fun getScreenWidth(): Int {
        val wm = context
            ?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        return wm.defaultDisplay.width
    }
}
