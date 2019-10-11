package com.djc.imtalk.widget

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.djc.imtalk.R
import kotlinx.android.synthetic.main.widget_itemview_layout.view.*

/**
 * @author   夜斗
 * @date  2019/10/11
 */
class ItemView :
    FrameLayout {

    val clickView: ConstraintLayout by lazy {
        item_view_content
    }

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        LayoutInflater.from(context).inflate(R.layout.widget_itemview_layout, this)
        initAttr(attrs!!)
    }


    private fun initAttr(attrs:AttributeSet) {
        var attr: TypedArray = context.obtainStyledAttributes(attrs,R.styleable.ItemView)
        var titleText = attr.getString(R.styleable.ItemView_titleText)
        item_view_title.text = titleText


        attr.recycle()
    }

    fun setOnItemViewClickListener(listener: View.OnClickListener) {
        clickView.setOnClickListener {
            listener.onClick(this)
        }
    }

}