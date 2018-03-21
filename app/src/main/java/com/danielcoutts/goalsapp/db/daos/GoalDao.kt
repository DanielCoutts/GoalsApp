package com.danielcoutts.goalsapp.db.daos

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import com.danielcoutts.goalsapp.etc.Recurrence
import com.danielcoutts.goalsapp.db.entities.Goal

@Dao
interface GoalDao {

    @Query("select * from goals")
    fun goals(): LiveData<List<Goal>>

    @Query("select * from goals where recurrence = :recurrence")
    fun goals(recurrence: Recurrence) : LiveData<List<Goal>>

    @Query("select * from goals where id = :id")
    fun goal(id: Long): LiveData<Goal>

    @Insert(onConflict = REPLACE)
    fun insertGoal(goal: Goal)

    @Update(onConflict = REPLACE)
    fun updateGoal(goal: Goal)

    @Delete
    fun deleteGoal(goal: Goal)

}