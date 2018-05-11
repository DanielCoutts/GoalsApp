package com.danielcoutts.goalsapp.db.daos

import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import com.danielcoutts.goalsapp.etc.Recurrence
import com.danielcoutts.goalsapp.db.entities.Goal
import io.reactivex.Flowable

@Dao
interface GoalDao {

    @Query("select * from goals")
    fun goals(): Flowable<List<Goal>>

    @Query("select * from goals where recurrence = :recurrence")
    fun goals(recurrence: Recurrence) : Flowable<List<Goal>>

    @Query("select * from goals where id = :id")
    fun goal(id: Long): Flowable<Goal>

    @Insert(onConflict = REPLACE)
    fun insertGoal(goal: Goal)

    @Update(onConflict = REPLACE)
    fun updateGoal(goal: Goal)

    @Delete
    fun deleteGoal(goal: Goal)

}