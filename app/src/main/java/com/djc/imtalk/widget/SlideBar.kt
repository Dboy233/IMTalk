package com.djc.imtalk.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.djc.imtalk.R
import org.jetbrains.anko.sp

/**
 *@author ： created by dujiangchuan
 * 时间    ：2019/4/10 21
 * 邮箱    ：894230813@qq.com
 */
class SlideBar(context: Context?, attrs: AttributeSet? = null) : View(context, attrs) {
    private var sectionHeight = 0F
    private var paint = Paint()
    private var textBaseLine = 0f
    var onSectionChangeListener: OnSectionChangeListener? = null

    companion object {
        private val SECTIONS = arrayOf(
            "A", "B", "C", "D", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S",
            "T", "U", "V", "W", "X", "Y", "Z"
        )
    }

    init {
        paint.apply {
            color = resources.getColor(R.color.blue_light)//画笔颜色
            textSize = sp(12).toFloat()//画笔大小
            textAlign = Paint.Align.CENTER//图像居中
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        //计算每个字符分配的高度
        sectionHeight = h / SECTIONS.size * 1.0f
        val fontMetrics = paint.fontMetrics
        val textHeight = fontMetrics.descent - fontMetrics.ascent
        textBaseLine = sectionHeight / 2 + (textHeight / 2 - fontMetrics.descent)


    }

    override fun onDraw(canvas: Canvas) {
        val x = width * 1.0f / 2
        var baseLine = textBaseLine
        SECTIONS.forEach {
            canvas.drawText(it, x, baseLine, paint)
            //更新y值
            baseLine += sectionHeight
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                setBackgroundResource(R.drawable.bg_slide_bar)
                //获取字母
                val index = getTouchIndex(event)
                val firstLetter = SECTIONS[index]
                onSectionChangeListener?.onSectionChange(firstLetter)
            }
            MotionEvent.ACTION_UP -> {
                setBackgroundColor(Color.TRANSPARENT)
                onSectionChangeListener?.onSlideFinish()
            }
            MotionEvent.ACTION_MOVE -> {
                val index = getTouchIndex(event)
                val firstLetter = SECTIONS[index]
                onSectionChangeListener?.onSectionChange(firstLetter)
            }

        }

        return true
    }

    //获取点击位置的下标
    private fun getTouchIndex(event: MotionEvent): Int {
        var index = (event.y / sectionHeight).toInt()
        //越界检查
        if (index < 0) {
            index = 0
        } else if (index >= SECTIONS.size) {
            index = SECTIONS.size - 1
        }

        return index
    }

    //回调接口
    interface OnSectionChangeListener {
        fun onSectionChange(firstLetter: String)
        fun onSlideFinish()
    }
}