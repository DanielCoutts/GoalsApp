package com.danielcoutts.goalsapp.sections.main.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.danielcoutts.goalsapp.db.entities.*
import com.danielcoutts.goalsapp.etc.Recurrence
import com.danielcoutts.goalsapp.views.GoalView

class GoalListAdapter(private val recurrence: Recurrence) : RecyclerView.Adapter<GoalListAdapter.GoalViewHolder>() {

    var goals: List<Goal> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var logs: Map<Long, GoalLog> = mapOf()
        set(value) {
            field = value;
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoalViewHolder = GoalViewHolder(GoalView(parent.context))

    override fun getItemCount(): Int = goals.size

    override fun onBindViewHolder(holder: GoalViewHolder, position: Int) {
        val item = goals[position]

        holder.bind(item)
    }

    fun updateGoalLogs(goalLogs: List<TimeGoalLog>) {
        for (goal in goals) {
            for (log in goalLogs) {
                if ()
            }
        }
        notifyDataSetChanged()
    }

    fun updateGoalLogs(goalLogs: List<NumberGoalLog>) {

    }

    fun updateGoalLogs(goalLogs: List<CheckboxGoalLog>) {

    }

    class GoalViewHolder(val goalItemView: GoalView) : RecyclerView.ViewHolder(goalItemView) {
        fun bind(item: Goal) = with(goalItemView) {
            goal = item
        }
    }
}