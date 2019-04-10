package com.djc.imtalk.ui.activity

import com.djc.imtalk.R
import com.djc.imtalk.factory.FragmentFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override fun getLayoutResId(): Int = R.layout.activity_main

    override fun init() {
        super.init()
        //选项卡切换
        bottom_bar.setOnNavigationItemSelectedListener { item ->
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_frame, FragmentFactory.instance.getFragment(item.itemId))
                .commit()
            true
        }

    }
}
