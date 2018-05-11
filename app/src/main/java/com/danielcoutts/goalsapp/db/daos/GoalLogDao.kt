package com.danielcoutts.goalsapp.db.daos

import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import com.danielcoutts.goalsapp.db.entities.*
import com.danielcoutts.goalsapp.etc.Recurrence
import io.reactivex.Flowable
import org.joda.time.LocalDate

@Dao
interface GoalLogDao {

    @Query("select * from goal_logs where date = :date")
    fun logs(date: LocalDate): Flowable<List<GoalLog>>

    @Query("select * from goal_logs where recurrence = :recurrence and date = :date")
    fun logs(date: LocalDate, recurrence: Recurrence): Flowable<List<GoalLog>>

    @Query("select * from goal_logs where goalId = :goalId and date = :date")
    fun log(goalId: Long, date: LocalDate): Flowable<GoalLog>

    @Insert(onConflict = REPLACE)
    fun insertLog(log: GoalLog)

    @Update(onConflict = REPLACE)
    fun updateLog(log: GoalLog)
}