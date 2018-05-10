package com.danielcoutts.goalsapp.sections.main.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.danielcoutts.goalsapp.db.entities.Goal
import com.danielcoutts.goalsapp.etc.Recurrence
import com.danielcoutts.goalsapp.views.GoalView

class GoalListAdapter(private val recurrence: Recurrence) : RecyclerView.Adapter<GoalListAdapter.GoalViewHolder>() {

    var items: List<Goal> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoalViewHolder = GoalViewHolder(GoalView(parent.context))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: GoalViewHolder, position: Int) {
        val item = items[position]

        holder.bind(item)
    }

    class GoalViewHolder(val goalItemView: GoalView) : RecyclerView.ViewHolder(goalItemView) {
        fun bind(item: Goal) = with(goalItemView) {
            goal = item
        }
    }
}