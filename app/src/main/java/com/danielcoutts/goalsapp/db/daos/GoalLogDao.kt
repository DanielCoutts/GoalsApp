package com.danielcoutts.goalsapp.db.daos

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.danielcoutts.goalsapp.db.entities.*
import com.danielcoutts.goalsapp.etc.LocalDateConstants
import com.danielcoutts.goalsapp.etc.Recurrence
import io.reactivex.Flowable
import org.joda.time.LocalDate

@Dao
abstract class GoalLogDao {

    @Query("select * from goal_logs where date = :date")
    abstract fun logs(date: LocalDate): Flowable<List<GoalLog>>

    @Query("select * from goal_logs where recurrence = :recurrence and date = :date")
    abstract fun logs(date: LocalDate, recurrence: Recurrence): Flowable<List<GoalLog>>

    @Query("select * from goal_logs where goalId = :goalId and date = :date")
    abstract fun log(goalId: Long, date: LocalDate): Flowable<GoalLog>

    @Query("select * from goal_logs where goalId = :goalId and date = :date")
    abstract fun getLog(goalId: Long, date: LocalDate): GoalLog?

    @Insert(onConflict = REPLACE)
    abstract fun insertLog(log: GoalLog)

    @Update(onConflict = REPLACE)
    abstract fun updateLog(log: GoalLog)

    @Transaction
    open fun logForGoal(goal: Goal) {
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