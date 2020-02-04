package com.danielcoutts.goalsapp.repository.db.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.danielcoutts.goalsapp.repository.db.entities.*
import com.danielcoutts.goalsapp.repository.etc.LocalDateValues
import com.danielcoutts.goalsapp.repository.etc.Recurrence
import java.time.LocalDate

@Dao
interface GoalLogDao {

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

    @Update(onConflict = REPLACE)
    suspend fun updateLog(log: GoalLogEntity)

    @Transaction
    suspend fun logForGoal(goal: GoalEntity) {
        val date: LocalDate = when(goal.recurrence) {
            Recurrence.DAILY -> LocalDateValues.today
            Recurrence.WEEKLY -> LocalDateValues.week
            Recurrence.MONTHLY -> LocalDateValues.month
        }

        var log = getLog(goal.id, date)

        if (log == null) {
            log = GoalLogEntity(goal.id, goal.recurrence, goal.goalType, date, 1)
            insertLog(log)
        }
        else if (log.numberLogged < goal.target) {
            log.numberLogged++
            updateLog(log)
        }
    }
}