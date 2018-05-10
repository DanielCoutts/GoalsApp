package com.danielcoutts.goalsapp.views

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import com.danielcoutts.goalsapp.R
import com.danielcoutts.goalsapp.db.entities.Goal

class GoalView @kotlin.jvm.JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    var goal: Goal? = null
        set(value) {
            field = value
            // TODO Update UI to reflect
        }

    init {
        inflate(getContext(), R.layout.item_goal, this)
    }
}