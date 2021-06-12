package com.arulvakku.lyrics.app.utilities

import android.R.attr
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.text.style.ReplacementSpan


class RoundedBackgroundSpan(private var bgColor: Int, private var textColor: Int, radius: Int) :
    ReplacementSpan() {
    private var mRadius = radius
    private var mSize = 0


    override fun getSize(
        paint: Paint,
        text: CharSequence?,
        start: Int,
        end: Int,
        fm: Paint.FontMetricsInt?
    ): Int {
        mSize =  (paint.measureText(text, start, end) + 2 * mRadius).toInt()
        return mSize+5
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
        val defaultColor = paint.color //Save text color

        val defaultStrokeWidth = paint.strokeWidth

        //Draw a rounded rectangle

        //Draw a rounded rectangle
        paint.color = bgColor
        paint.style = Paint.Style.STROKE
        paint.setStrokeWidth(1F)
        paint.isAntiAlias = true
        val rectF = RectF(
            attr.x + 2.5f, attr.y + 2.5f + paint.ascent(),
            (attr.x + mSize).toFloat(), attr.y + paint.descent()
        )
        //Set the text background rectangle, x is the x value of the span's upper left corner relative to the entire TextView, and y is the y value of the span's upper left corner relative to the entire View.
        // paint.ascent() gets the upper edge of the text, paint.descent() gets the lower edge of the text
        //x+2.5f solves the problem of inconsistent line thickness
        //Set the text background rectangle, x is the x value of the span's upper left corner relative to the entire TextView, and y is the y value of the span's upper left corner relative to the entire View.
        // paint.ascent() gets the upper edge of the text, paint.descent() gets the lower edge of the text
        //x+2.5f solves the problem of inconsistent line thickness
        canvas.drawRoundRect(rectF, mRadius.toFloat(), mRadius.toFloat(), paint)

        //Draw text

        //Draw text
        paint.color = textColor
        paint.style = Paint.Style.FILL
        paint.strokeWidth = defaultStrokeWidth
        canvas.drawText(
            text!!,
            start,
            end,
            attr.x + mRadius.toFloat(),
            attr.y.toFloat(),
            paint
        ) //Here mRadius is the distance to the right of the text


        paint.color = defaultColor //Restore the text color of the brush

    }

}