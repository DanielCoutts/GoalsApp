package com.danielcoutts.goalsapp.sections.main.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.danielcoutts.goalsapp.db.entities.*
import com.danielcoutts.goalsapp.etc.Recurrence
import com.danielcoutts.goalsapp.sections.main.data.GoalListData
import com.danielcoutts.goalsapp.views.GoalView

class GoalListAdapter(private val recurrence: Recurrence) : RecyclerView.Adapter<GoalListAdapter.GoalViewHolder>() {

    private var goals: List<Goal> = listOf()

    private var logs: Map<Long, GoalLog> = mapOf()

    fun setGoalListData(data: GoalListData) {
        goals = data.goals

        val newLogs = mutableMapOf<Long, GoalLog>()
        for (log in data.logs) newLogs[log.goalId] = log
        logs = newLogs

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoalViewHolder = GoalViewHolder(GoalView(parent.context))

    override fun getItemCount(): Int = goals.size

    override fun onBindViewHolder(holder: GoalViewHolder, position: Int) {
        val goal = goals[position]
        val log = logs[goal.id]

        holder.bind(goal, log)
    }

    class GoalViewHolder(val goalItemView: GoalView) : RecyclerView.ViewHolder(goalItemView) {
        fun bind(goal: Goal, log: GoalLog?) = with(goalItemView) {
            this.goal = goal
            this.log = log
        }
    }
}