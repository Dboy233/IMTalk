package com.djc.imtalk.factory

import android.support.v4.app.Fragment
import android.util.Log
import com.djc.imtalk.R
import com.djc.imtalk.ui.fragment.ContactFragment
import com.djc.imtalk.ui.fragment.ConversationFragment
import com.djc.imtalk.ui.fragment.MoreFragment

/**
 *@author ： created by dujiangchuan
 * 时间    ：2019/4/9 20
 * 邮箱    ：894230813@qq.com
 */
class FragmentFactory private constructor() {


    private val conversation by lazy {
        Log.d("DJC111","conversation lazy")
        ConversationFragment()
    }
    //联系人
    private val contact by lazy {
        Log.d("TAG", "more")
        ContactFragment()
    }
    //更多
    private val more by lazy {

        MoreFragment()
    }

    companion object {
        val instance = FragmentFactory()
    }

    fun getFragment(tabId: Int): Fragment {
        return when (tabId) {
            R.id.tab_conversation -> conversation
            R.id.tab_contact -> contact
            R.id.tab_more -> more
            else -> conversation
        }
    }




}