package com.danielcoutts.goalsapp.data.db.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.danielcoutts.goalsapp.data.db.entities.*
import com.danielcoutts.goalsapp.data.db.etc.LocalDateValues
import com.danielcoutts.goalsapp.data.models.Recurrence
import java.time.LocalDate

@Dao
internal interface GoalLogDao {

    @Query("select * from goal_logs where date = :date")
    fun logs(date: LocalDate): LiveData<List<GoalLogEntity>>

    @Query("select * from goal_logs where recurrence = :recurrence and date = :date")
    fun logs(date: LocalDate, recurrence: Recurrence): LiveData<List<GoalLogEntity>>

    @Query("select * from goal_logs where goalId = :goalId and date = :date")
    suspend fun log(goalId: Long, date: LocalDate): GoalLogEntity

    @Query("select * from goal_logs where goalId = :goalId and date = :date")
    suspend fun getLog(goalId: Long, date: LocalDate): GoalLogEntity?

    @Insert(onConflict = REPLACE)
    suspend fun insertLog(log: GoalLogEntity)

    @Transaction
    suspend fun logForGoal(goal: GoalEntity, amount: Int) {
        val date: LocalDate = when (goal.recurrence) {
            Recurrence.DAILY -> LocalDateValues.today
            Recurrence.WEEKLY -> LocalDateValues.week
            Recurrence.MONTHLY -> LocalDateValues.month
        }

        val updatedLog = getLog(goal.id, date)?.let {
            it.copy(numberLogged = it.numberLogged + amount)
        }

        val log = updatedLog
                ?: GoalLogEntity(
                        goalId = goal.id,
                        recurrence = goal.recurrence,
                        goalType = goal.goalType,
                        date = date,
                        numberLogged = amount
                )

        insertLog(log)
    }
}