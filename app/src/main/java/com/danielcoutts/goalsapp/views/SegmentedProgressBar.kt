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


class SegmentedProgressBar @JvmOverloads constructor(
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

    var numberOfSegments = 2
        set(value) {
            field = when {
                (value < 2) -> 2
                else -> value
            }

            invalidate()
        }

    var numberOfActiveSegments = 0
        set(value) {
            val lastNumber = field
            field = when {
                (value < 0) -> 0
                (value > numberOfSegments) -> numberOfSegments
                else -> value
            }

            animateProgress(lastNumber, numberOfActiveSegments)
        }

    var spaceBetweenSegments = 0.0f
        set(value) {
            field = when {
                (value < 0) -> 0.0f
                else -> value
            }

            invalidate()
        }

    private val linePaint = Paint()
    private val roundedLinePaint = Paint()

    var animationDurationMillis = 300L

    var animator: ValueAnimator? = null

    init {
        val typedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.SegmentedProgressBar, defStyleAttr, 0)

        try {
            backgroundLineColor = typedArray.getColor(R.styleable.SegmentedProgressBar_backgroundLineColor, Color.LTGRAY)
            foregroundLineColor = typedArray.getColor(R.styleable.SegmentedProgressBar_foregroundLineColor, Color.DKGRAY)
            lineThickness = typedArray.getDimension(R.styleable.SegmentedProgressBar_lineThickness, 0.0f)
            numberOfSegments = typedArray.getInteger(R.styleable.SegmentedProgressBar_numberOfSegments, 0)
            spaceBetweenSegments = typedArray.getDimension(R.styleable.SegmentedProgressBar_spaceBetweenSegments, 0.0f)
        } finally {
            typedArray.recycle()
        }

        linePaint.style = Paint.Style.STROKE
        linePaint.strokeCap = Paint.Cap.BUTT
        linePaint.isAntiAlias = true

        roundedLinePaint.style = Paint.Style.STROKE
        roundedLinePaint.strokeCap = Paint.Cap.ROUND
        roundedLinePaint.isAntiAlias = true
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

        linePaint.strokeWidth = lineThickness
        roundedLinePaint.strokeWidth = lineThickness

        drawSegments(canvas)
    }

    private fun drawSegments(canvas: Canvas?) {
        val y = height.toFloat() / 2
        val offset = lineThickness / 2
        val start = offset
        val maxLength = width.toFloat() - (offset * 2)

        val numberOfSpaces = numberOfSegments - 1
        val segmentLength = (maxLength - (spaceBetweenSegments * numberOfSpaces)) / numberOfSegments

        for (i in 0 until numberOfSegments) {
            val segmentStartPosition = start + (segmentLength + spaceBetweenSegments) * i
            val segmentEndPosition = segmentStartPosition + segmentLength

            linePaint.color = if (i < numberOfActiveSegments) foregroundLineColor else backgroundLineColor
            roundedLinePaint.color = linePaint.color
            canvas?.drawLine(segmentStartPosition, y, segmentEndPosition, y, linePaint)
            when {
                (i == 0) -> canvas?.drawLine(segmentStartPosition, y, segmentStartPosition+0.001f, y, roundedLinePaint)
                (i == numberOfSegments - 1) -> canvas?.drawLine(segmentEndPosition-0.001f, y, segmentEndPosition, y, roundedLinePaint)
            }
        }
    }
    private fun animateProgress(startNumber: Int, endNumber: Int) {

        if (animator?.isRunning == true) return

        animator = ValueAnimator.ofInt(startNumber, endNumber)
        animator?.duration = animationDurationMillis
        animator?.addUpdateListener { animation ->
            numberOfActiveSegments = animation.animatedValue as Int
            invalidate()
        }
        animator?.start()
    }
}