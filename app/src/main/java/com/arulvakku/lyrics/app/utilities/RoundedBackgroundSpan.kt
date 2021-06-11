package com.arulvakku.lyrics.app.utilities

import android.R.attr
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.text.style.ReplacementSpan


class RoundedBackgroundSpan : ReplacementSpan() {
    override fun getSize(
        paint: Paint,
        text: CharSequence?,
        start: Int,
        end: Int,
        fm: Paint.FontMetricsInt?
    ): Int {
        return 0
    }

    override fun draw(
        canvas: Canvas,
        text: CharSequence?,
        start: Int,
        end: Int,
        x: Float,
        top: Int,
        y: Int,
        bottom: Int,
        paint: Paint
    ) {
        val rect = RectF(attr.x.toFloat(), top.toFloat(), (attr.x + text!!.length).toFloat(), bottom.toFloat())
        paint.color = Color.GRAY
        canvas.drawRoundRect(rect, 20f, 20f, paint)
        paint.color = Color.BLACK
        canvas.drawText(text, start, end, attr.x.toFloat(), attr.y.toFloat(), paint)
    }
}