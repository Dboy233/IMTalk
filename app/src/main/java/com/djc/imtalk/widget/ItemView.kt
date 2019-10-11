package com.djc.imtalk.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.djc.imtalk.R

/**
 * @author   夜斗
 * @date  2019/10/11
 */
class ItemView(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
    FrameLayout(context, attrs, defStyleAttr) {


    init {
        LayoutInflater.from(context).inflate(R.layout.widget_itemview_layout, this)



    }


}