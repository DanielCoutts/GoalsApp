package com.danielcoutts.goalsapp.db.daos

import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import com.danielcoutts.goalsapp.db.entities.*
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

    @Insert(onConflict = REPLACE)
    abstract fun insertLog(log: GoalLog)

    @Update(onConflict = REPLACE)
    abstract fun updateLog(log: GoalLog)

    @Transaction
    open fun logForGoal(goal: Goal) {

    }
}