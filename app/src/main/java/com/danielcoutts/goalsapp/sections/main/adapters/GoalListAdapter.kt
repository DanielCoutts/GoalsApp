package com.danielcoutts.goalsapp.sections.main.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.danielcoutts.goalsapp.data.db.entities.*
import com.danielcoutts.goalsapp.data.models.Goal
import com.danielcoutts.goalsapp.data.models.GoalLog
import com.danielcoutts.goalsapp.sections.main.data.GoalListData
import com.danielcoutts.goalsapp.views.GoalView
import kotlinx.android.synthetic.main.item_goal.view.*

class GoalListAdapter : RecyclerView.Adapter<GoalListAdapter.GoalViewHolder>() {

    data class ListItem(
            val goal: Goal,
            val log: GoalLog?
    )

    private var listItems = listOf<ListItem>()

    var goalDeleteListener: ((Goal) -> Unit)? = null
    var goalLogListener: ((Goal) -> Unit)? = null

    fun setGoalListData(data: GoalListData) {
        val oldListItems = listItems
        val newListItems = data.goals.map { goal ->
            ListItem(
                    goal = goal,
                    log = data.logs.find { log -> log.goalId == goal.id }
            )
        }

        listItems = newListItems

        val diffResult = DiffUtil.calculateDiff(DiffUtilCallback(oldListItems, newListItems))
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoalViewHolder = GoalViewHolder(GoalView(parent.context))

    override fun getItemCount(): Int = listItems.size

    override fun onBindViewHolder(holder: GoalViewHolder, position: Int) {
        val item = listItems[position]

        holder.bind(item.goal, item.log)
    }

    inner class GoalViewHolder(val goalItemView: GoalView) : RecyclerView.ViewHolder(goalItemView) {
        fun bind(goal: Goal, log: GoalLog?) {
            with(goalItemView) {
                this.goal = goal as Goal.Number
                this.log = log as? GoalLog.Number

                logButton.setOnClickListener {
                    goalLogListener?.invoke(goal)
                }

                deleteButton.setOnClickListener {
                    goalDeleteListener?.invoke(goal)
                }
            }
        }
    }

    inner class DiffUtilCallback(
            private val oldList: List<ListItem>,
            private val newList: List<ListItem>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldItem = oldList[oldItemPosition]
            val newItem = newList[newItemPosition]

            return oldItem.goal.id == newItem.goal.id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldItem = oldList[oldItemPosition]
            val newItem = newList[newItemPosition]

            return oldItem == newItem
        }
    }
}