package com.backbase.assignment.ui.views

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.backbase.assignment.R


class RatingView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private var arcProportion: Float = 0f

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        val backgroundColor = ContextCompat.getColor(context, R.color.rating_view_background)
        val less50Color = ContextCompat.getColor(context, R.color.rating_view_less_50)
        val greate50Color = ContextCompat.getColor(context, R.color.rating_view_great_50)
        val greate50LeftColor = ContextCompat.getColor(context, R.color.rating_view_great_50_left)
        val less50LeftColor = ContextCompat.getColor(context, R.color.rating_view_less_50_left)

        val mPaintBackground = Paint(Paint.ANTI_ALIAS_FLAG)
        mPaintBackground.color = backgroundColor
        val backgroudRadius = width / 2F

        val mPaintPercentage = Paint(Paint.ANTI_ALIAS_FLAG)
        mPaintPercentage.strokeWidth = backgroudRadius * 10F / 100;
        mPaintPercentage.style = Paint.Style.STROKE
        mPaintPercentage.color = if (arcProportion < 0.5) less50Color else greate50Color
        mPaintPercentage.strokeCap = Paint.Cap.ROUND

        val mPaintPercentageLeft = Paint(Paint.ANTI_ALIAS_FLAG)
        mPaintPercentageLeft.strokeWidth = backgroudRadius * 10F / 100;
        mPaintPercentageLeft.style = Paint.Style.STROKE
        mPaintPercentageLeft.color = if (arcProportion < 0.5) less50LeftColor else greate50LeftColor

        val percentageRadius = backgroudRadius * 15F / 100

        val x = width / 2F
        val y = height / 2F
        //Draw background
        canvas?.drawCircle(x, y, backgroudRadius, mPaintBackground)

        val rectangle =
            RectF(
                percentageRadius,
                percentageRadius,
                width.toFloat() - percentageRadius,
                height.toFloat() - percentageRadius
            )

        //Draw progress circle
        canvas?.drawArc(
            rectangle,
            -90F + arcProportion * 360,
            (1 - arcProportion) * 360,
            false,
            mPaintPercentageLeft
        )

        //Draw progress circle
        canvas?.drawArc(
            rectangle, 270F, arcProportion * 360, false, mPaintPercentage
        )

        val percentage = (arcProportion * 100).toInt()
        val percentageSymbol = "%"

        val textPaint = TextPaint()
        val textPaintSymbol = TextPaint()

        textPaint.isAntiAlias = true
        textPaint.textSize = height / 3F
        textPaint.color = Color.WHITE

        textPaintSymbol.isAntiAlias = true
        textPaintSymbol.textSize = textPaint.textSize / 2.5F
        textPaintSymbol.color = Color.WHITE

        val boundsPercentageSymbols = Rect()
        textPaintSymbol.getTextBounds(
            percentageSymbol,
            0,
            percentageSymbol.length,
            boundsPercentageSymbols
        )
        val symbolWidth = textPaintSymbol.measureText(percentageSymbol).toInt()
        val symbolHeight = boundsPercentageSymbols.height()

        val boundsPercentage = Rect()
        textPaint.getTextBounds(
            percentage.toString(),
            0,
            percentage.toString().length,
            boundsPercentage
        )
        val width = textPaint.measureText(percentage.toString()).toInt()
        val height = boundsPercentage.height()
        val widthSum = width + symbolWidth

        //Draw text
        canvas?.drawText(percentage.toString(), x - (widthSum / 2), y + height / 2, textPaint)
        canvas?.drawText("%", x + width / 2, y - symbolHeight / 2, textPaintSymbol)

    }

    /**
     * @param arcProportion The proportion of the semi circle arc, from 0 to 1. Setting 0 makes the arc invisible, and 1
     * makes a whole circle.
     */
    fun setArcProportion(arcProportion: Float) {
        this.arcProportion = arcProportion
        invalidate()
    }
}
