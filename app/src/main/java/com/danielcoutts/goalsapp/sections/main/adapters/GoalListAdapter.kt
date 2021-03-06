package com.danielcoutts.goalsapp.sections.main.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import com.danielcoutts.goalsapp.data.db.entities.*
import com.danielcoutts.goalsapp.data.models.Goal
import com.danielcoutts.goalsapp.data.models.GoalLog
import com.danielcoutts.goalsapp.sections.main.data.GoalListData
import com.danielcoutts.goalsapp.views.GoalView
import kotlinx.android.synthetic.main.item_goal.view.*

class GoalListAdapter : RecyclerView.Adapter<GoalListAdapter.GoalViewHolder>() {

    private var goals: List<Goal> = listOf()

    private var logs: Map<Long, GoalLog> = mapOf()

    var goalDeleteListener: ((Goal) -> Unit)? = null
    var goalLogListener: ((Goal) -> Unit)? = null

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

        holder.goalItemView.logButton.setOnClickListener {
            goalLogListener?.invoke(goal)
        }

        holder.goalItemView.deleteButton.setOnClickListener {
            goalDeleteListener?.invoke(goal)
        }
    }

    class GoalViewHolder(val goalItemView: GoalView) : RecyclerView.ViewHolder(goalItemView) {
        fun bind(goal: Goal, log: GoalLog?) {
            with(goalItemView) {
                this.goal = goal as Goal.Number
                this.log = log as? GoalLog.Number
            }
        }
    }
}