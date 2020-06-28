package com.danielcoutts.goalsapp.data.db.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.danielcoutts.goalsapp.data.models.Recurrence
import com.danielcoutts.goalsapp.data.db.entities.GoalEntity

@Dao
internal interface GoalDao {

    @Query("select * from goals")
    fun goals(): LiveData<List<GoalEntity>>

    @Query("select * from goals where recurrence = :recurrence")
    fun goals(recurrence: Recurrence) : LiveData<List<GoalEntity>>

    @Query("select * from goals where id = :id")
    suspend fun goal(id: Long): GoalEntity

    @Insert(onConflict = REPLACE)
    suspend fun insertGoal(goal: GoalEntity)

    @Update(onConflict = REPLACE)
    suspend fun updateGoal(goal: GoalEntity)

    @Delete
    suspend fun deleteGoal(goal: GoalEntity)

}