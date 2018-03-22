package com.danielcoutts.goalsapp.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.danielcoutts.goalsapp.R
import android.animation.ValueAnimator
import android.view.animation.DecelerateInterpolator


class LinearProgressBar @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    var backgroundLineColor = Color.LTGRAY
        set(value) {
            field = value
            invalidate()
        }
    var foregroundLineColor = Color.DKGRAY
        set(value) {
            field = value
            invalidate()
        }
    var lineThickness = 0.0f
        set(value) {
            field = value
            invalidate()
        }

    private val backgroundLinePaint = Paint()
    private val foregroundLinePaint = Paint()

    var animationDurationMillis = 300L

    var animator: ValueAnimator? = null

    var percentage: Float = 0.01f
        set(value) {
            val lastPercentage = field

            field = when {
                (value < 0.01) -> 0.01f
                (value > 1) -> 1.0f
                else -> value
            }

            animateProgress(lastPercentage, percentage)
        }

    init {
        val typedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.LinearProgressBar, defStyleAttr, 0)

        try {
            backgroundLineColor = typedArray.getColor(R.styleable.LinearProgressBar_backgroundLineColor, Color.LTGRAY)
            foregroundLineColor = typedArray.getColor(R.styleable.LinearProgressBar_foregroundLineColor, Color.DKGRAY)
            lineThickness = typedArray.getDimension(R.styleable.LinearProgressBar_lineThickness, 0.0f)
        } finally {
            typedArray.recycle()
        }

        backgroundLinePaint.style = Paint.Style.STROKE
        backgroundLinePaint.strokeCap = Paint.Cap.ROUND
        backgroundLinePaint.isAntiAlias = true

        foregroundLinePaint.style = Paint.Style.STROKE
        foregroundLinePaint.strokeCap = Paint.Cap.ROUND
        foregroundLinePaint.isAntiAlias = true
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)

        val width = when (widthMode) {
            MeasureSpec.EXACTLY -> widthSize
            MeasureSpec.AT_MOST -> Math.min(lineThickness.toInt(), widthSize)
            MeasureSpec.UNSPECIFIED -> lineThickness.toInt()
            else -> lineThickness.toInt()
        }

        val height = when (heightMode) {
            MeasureSpec.EXACTLY -> heightSize
            MeasureSpec.AT_MOST -> Math.min(lineThickness.toInt(), heightSize)
            MeasureSpec.UNSPECIFIED -> lineThickness.toInt()
            else -> lineThickness.toInt()
        }

        setMeasuredDimension(width, height)

    }

    override fun onDraw(canvas: Canvas?) {
        val y = height.toFloat() / 2
        val offset = lineThickness / 2
        val start = offset
        val maxLength = width.toFloat() - (offset * 2)

        backgroundLinePaint.color = backgroundLineColor
        backgroundLinePaint.strokeWidth = lineThickness

        foregroundLinePaint.color = foregroundLineColor
        foregroundLinePaint.strokeWidth = lineThickness

        canvas?.drawLine(start, y, start + maxLength, y, backgroundLinePaint)
        canvas?.drawLine(start, y, start + (maxLength * percentage), y, foregroundLinePaint)
    }

    fun animateProgress(startPercentage: Float, endPercentage: Float) {

        if (animator?.isRunning == true) return

        animator = ValueAnimator.ofFloat(startPercentage, endPercentage)
        animator?.duration = animationDurationMillis
        animator?.interpolator = DecelerateInterpolator()
        animator?.addUpdateListener { animation ->
            percentage = animation.animatedValue as Float
            invalidate()
        }
        animator?.start()
    }
}