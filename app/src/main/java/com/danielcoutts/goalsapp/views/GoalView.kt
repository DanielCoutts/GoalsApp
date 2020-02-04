package com.danielcoutts.goalsapp.views

import android.content.Context
import androidx.core.content.ContextCompat
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.core.view.updateLayoutParams
import com.danielcoutts.goalsapp.R
import com.danielcoutts.goalsapp.data.db.entities.GoalEntity
import com.danielcoutts.goalsapp.data.db.entities.GoalLogEntity
import com.danielcoutts.goalsapp.data.models.Goal
import com.danielcoutts.goalsapp.data.models.GoalLog
import kotlinx.android.synthetic.main.item_goal.view.*

class GoalView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    var goal: Goal.Number? = null
        set(value) {
            field = value
            if (value == null) return

            lightText.text = "${value.recurrence} I want to"
            title.text = value.title
            progressBar.numberOfSegments = value.target
            loggedText.text = "0 logged"
            remainingText.text = "${value.target} remaining"
        }

    var log: GoalLog.Number? = null
        set(value) {
            field = value
            val currentGoal = goal
            if (value == null || currentGoal == null) return

            progressBar.numberOfActiveSegments = value.numberLogged
            loggedText.text = "${value.numberLogged} logged"
            remainingText.text = "${currentGoal.target - value.numberLogged} remaining"

            val logButtonActive = (currentGoal.target > value.numberLogged)
            logButton.isEnabled = logButtonActive
            logButton.setColorFilter(ContextCompat.getColor(context, if (logButtonActive) R.color.colorGreyDark else R.color.colorGreyLight))
        }

    init {
        inflate(getContext(), R.layout.item_goal, this)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        updateLayoutParams {
            width = LayoutParams.MATCH_PARENT
        }
    }
}