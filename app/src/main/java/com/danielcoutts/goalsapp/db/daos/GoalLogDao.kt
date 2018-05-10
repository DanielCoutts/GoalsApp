package com.danielcoutts.goalsapp.db.daos

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import com.danielcoutts.goalsapp.db.entities.*
import java.time.LocalDate

@Dao
interface GoalLogDao {

    @Query("select * from number_goal_logs where goalId = :goalId and date = :date")
    fun numberLog(goalId: Long, date: LocalDate): LiveData<NumberGoalLog>

    @Query("select * from time_goal_logs where goalId = :goalId and date = :date")
    fun timeLog(goalId: Long, date: LocalDate): LiveData<TimeGoalLog>

    @Query("select * from checkbox_goal_logs where goalId = :goalId and date = :date")
    fun checkboxLog(goalId: Long, date: LocalDate): LiveData<CheckboxGoalLog>

    @Insert(onConflict = REPLACE)
    fun insertLog(log: NumberGoalLog)

    @Update(onConflict = REPLACE)
    fun updateLog(log: NumberGoalLog)

    @Insert(onConflict = REPLACE)
    fun insertLog(log: TimeGoalLog)

    @Update(onConflict = REPLACE)
    fun updateLog(log: TimeGoalLog)

    @Insert(onConflict = REPLACE)
    fun insertLog(log: CheckboxGoalLog)

    @Update(onConflict = REPLACE)
    fun updateLog(log: CheckboxGoalLog)
}