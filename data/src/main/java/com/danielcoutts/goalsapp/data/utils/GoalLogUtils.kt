package com.danielcoutts.goalsapp.data.utils

import com.danielcoutts.goalsapp.data.models.GoalLog
import com.danielcoutts.goalsapp.data.db.entities.GoalLogEntity
import com.danielcoutts.goalsapp.data.db.etc.GoalType

internal fun GoalLog.toEntity() = GoalLogEntity(
        id = this.id,
        goalId = this.goalId,
        recurrence = this.recurrence,
        goalType = when (this) {
            is GoalLog.Time -> GoalType.TIME
            is GoalLog.Number -> GoalType.NUMBER
            is GoalLog.Checkbox -> GoalType.CHECKBOX
        },
        date = this.date,
        numberLogged = when (this) {
            is GoalLog.Time -> this.minsLogged
            is GoalLog.Number -> this.numberLogged
            is GoalLog.Checkbox -> 1
        }
)

internal fun GoalLogEntity.toGoalLog() =
        when (this.goalType) {
            GoalType.TIME -> GoalLog.Time(
                    id = this.id,
                    goalId = this.goalId,
                    recurrence = this.recurrence,
                    date = this.date,
                    minsLogged = this.numberLogged
            )
            GoalType.NUMBER -> GoalLog.Number(
                    id = this.id,
                    goalId = this.goalId,
                    recurrence = this.recurrence,
                    date = this.date,
                    numberLogged = this.numberLogged
            )
            GoalType.CHECKBOX -> GoalLog.Checkbox(
                    id = this.id,
                    goalId = this.goalId,
                    recurrence = this.recurrence,
                    date = this.date
            )
        }