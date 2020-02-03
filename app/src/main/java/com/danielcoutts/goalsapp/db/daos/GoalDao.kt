package com.danielcoutts.goalsapp.db.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.danielcoutts.goalsapp.etc.Recurrence
import com.danielcoutts.goalsapp.db.entities.Goal

@Dao
interface GoalDao {

    @Query("select * from goals")
    fun goals(): LiveData<List<Goal>>

    @Query("select * from goals where recurrence = :recurrence")
    fun goals(recurrence: Recurrence) : LiveData<List<Goal>>

    @Query("select * from goals where id = :id")
    suspend fun goal(id: Long): Goal

    @Insert(onConflict = REPLACE)
    suspend fun insertGoal(goal: Goal)

    @Update(onConflict = REPLACE)
    suspend fun updateGoal(goal: Goal)

    @Delete
    suspend fun deleteGoal(goal: Goal)

}