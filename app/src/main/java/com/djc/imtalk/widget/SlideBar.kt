package com.djc.imtalk.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.djc.imtalk.R
import org.jetbrains.anko.sp

/**
 *@author ： created by dujiangchuan
 * 时间    ：2019/4/10 21
 * 邮箱    ：894230813@qq.com
 */
class SlideBar(context: Context?, attrs: AttributeSet? = null) : View(context, attrs) {
    var sectionHeight = 0F
    var paint = Paint()
    var textBaseLine = 0f

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
        var baseLine=textBaseLine
        SECTIONS.forEach {
            canvas.drawText(it, x, baseLine, paint)
            //更新y值
            baseLine += sectionHeight
        }
    }

}