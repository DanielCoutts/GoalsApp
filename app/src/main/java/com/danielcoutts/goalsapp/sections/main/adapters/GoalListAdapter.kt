package com.danielcoutts.goalsapp.sections.main.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import com.danielcoutts.goalsapp.db.entities.*
import com.danielcoutts.goalsapp.sections.main.data.GoalListData
import com.danielcoutts.goalsapp.views.GoalView
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.item_goal.view.*

class GoalListAdapter : RecyclerView.Adapter<GoalListAdapter.GoalViewHolder>() {

    private var goals: List<Goal> = listOf()

    private var logs: Map<Long, GoalLog> = mapOf()

    val goalDeleteEvents = PublishSubject.create<Goal>()
    val goalLogEvents = PublishSubject.create<Goal>()

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
            goalLogEvents.onNext(goal)
        }

        holder.goalItemView.deleteButton.setOnClickListener {
            goalDeleteEvents.onNext(goal)
        }
    }

    class GoalViewHolder(val goalItemView: GoalView) : RecyclerView.ViewHolder(goalItemView) {
        fun bind(goal: Goal, log: GoalLog?) {
            with(goalItemView) {
                this.goal = goal
                this.log = log
            }
        }
    }
}