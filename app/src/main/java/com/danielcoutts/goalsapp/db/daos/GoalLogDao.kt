package com.danielcoutts.goalsapp.db.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.danielcoutts.goalsapp.db.entities.*
import com.danielcoutts.goalsapp.etc.LocalDateConstants
import com.danielcoutts.goalsapp.etc.Recurrence
import java.time.LocalDate

@Dao
interface GoalLogDao {

    @Query("select * from goal_logs where date = :date")
    fun logs(date: LocalDate): LiveData<List<GoalLog>>

    @Query("select * from goal_logs where recurrence = :recurrence and date = :date")
    fun logs(date: LocalDate, recurrence: Recurrence): LiveData<List<GoalLog>>

    @Query("select * from goal_logs where goalId = :goalId and date = :date")
    suspend fun log(goalId: Long, date: LocalDate): GoalLog

    @Query("select * from goal_logs where goalId = :goalId and date = :date")
    suspend fun getLog(goalId: Long, date: LocalDate): GoalLog?

    @Insert(onConflict = REPLACE)
    suspend fun insertLog(log: GoalLog)

    @Update(onConflict = REPLACE)
    suspend fun updateLog(log: GoalLog)

    @Transaction
    suspend fun logForGoal(goal: Goal) {
        val date: LocalDate = when(goal.recurrence) {
            Recurrence.DAILY -> LocalDateConstants.today
            Recurrence.WEEKLY -> LocalDateConstants.week
            Recurrence.MONTHLY -> LocalDateConstants.month
        }

        var log = getLog(goal.id, date)

        if (log == null) {
            log = GoalLog(goal.id, goal.recurrence, date, 1)
            insertLog(log)
        }
        else if (log.numberLogged < goal.target) {
            log.numberLogged++
            updateLog(log)
        }
    }
}