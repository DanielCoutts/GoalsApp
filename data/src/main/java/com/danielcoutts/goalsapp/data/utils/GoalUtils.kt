package com.danielcoutts.goalsapp.data.utils

import com.danielcoutts.goalsapp.data.models.Goal
import com.danielcoutts.goalsapp.data.db.entities.GoalEntity
import com.danielcoutts.goalsapp.data.db.etc.GoalType

internal fun Goal.toEntity() = GoalEntity(
        id = this.id,
        title = this.title,
        recurrence = this.recurrence,
        goalType = when (this) {
            is Goal.Time -> GoalType.TIME
            is Goal.Number -> GoalType.NUMBER
            is Goal.Checkbox -> GoalType.CHECKBOX
        },
        target = when (this) {
            is Goal.Time -> this.targetMins
            is Goal.Number -> this.target
            is Goal.Checkbox -> 1
        },
        dateCreated = this.dateCreated
)

internal fun GoalEntity.toGoal() =
        when (this.goalType) {
            GoalType.TIME -> Goal.Time(
                    id = this.id,
                    title = this.title,
                    recurrence = this.recurrence,
                    dateCreated = this.dateCreated,
                    targetMins = this.target
            )
            GoalType.NUMBER -> Goal.Number(
                    id = this.id,
                    title = this.title,
                    recurrence = this.recurrence,
                    dateCreated = this.dateCreated,
                    target = this.target
            )
            GoalType.CHECKBOX -> Goal.Checkbox(
                    id = this.id,
                    title = this.title,
                    recurrence = this.recurrence,
                    dateCreated = this.dateCreated
            )
        }