package com.danielcoutts.goalsapp.views

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import com.danielcoutts.goalsapp.R
import com.danielcoutts.goalsapp.db.entities.Goal
import com.danielcoutts.goalsapp.db.entities.GoalLog
import kotlinx.android.synthetic.main.item_goal.view.*

class GoalView @kotlin.jvm.JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    var goal: Goal? = null
        set(value) {
            field = value
            if (value == null) return

            lightText.text = "${value.recurrence} I want to"
            title.text = value.title
            progressBar.numberOfSegments = value.target
            loggedText.text = "0 logged"
            remainingText.text = "${value.target} remaining"
        }

    var log: GoalLog? = null
        set(value) {
            field = value
            val currentGoal = goal
            if (value == null || currentGoal == null) return

            progressBar.numberOfActiveSegments = value.numberLogged
            loggedText.text = "${value.numberLogged} logged"
            remainingText.text = "${currentGoal.target - value.numberLogged} remaining"
        }

    init {
        inflate(getContext(), R.layout.item_goal, this)
    }
}