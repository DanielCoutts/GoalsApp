package com.danielcoutts.goalsapp.sections.main.fragments


import android.app.AlertDialog
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.danielcoutts.goalsapp.R

import com.danielcoutts.goalsapp.base.BaseFragment
import com.danielcoutts.goalsapp.repository.db.entities.GoalEntity
import com.danielcoutts.goalsapp.sections.main.MainViewModel
import com.danielcoutts.goalsapp.sections.main.adapters.GoalListAdapter
import kotlinx.android.synthetic.main.fragment_goal_list.view.*

open class BaseGoalListFragment : BaseFragment() {

    protected val viewModel: MainViewModel by activityViewModels()

    protected val goalListAdapter = GoalListAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_goal_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = goalListAdapter
        }

        goalListAdapter.goalLogListener = { goal ->
            showLogDialogForGoal(goal)
        }

        goalListAdapter.goalDeleteListener = { goal ->
            showDeleteDialogForGoal(goal)
        }
    }

    private fun showDeleteDialogForGoal(goal: GoalEntity) {
        AlertDialog.Builder(context, android.R.style.Theme_Material_Light_Dialog_Alert)
                .setTitle(goal.title)
                .setMessage("Are you sure you want to delete this goal?")
                .setPositiveButton("Delete") { _, _ ->
                    viewModel.deleteGoal(goal)
                }
                .setNegativeButton("Cancel", null)
                .setIcon(R.drawable.ic_delete)
                .create()
                .show()
    }

    private fun showLogDialogForGoal(goal: GoalEntity) {
        AlertDialog.Builder(context, android.R.style.Theme_Material_Light_Dialog_Alert)
                .setTitle(goal.title)
                .setMessage("Are you sure you want to log this goal?")
                .setPositiveButton("Log") { _, _ ->
                    viewModel.logForGoal(goal)
                }
                .setNegativeButton("Cancel", null)
                .setIcon(R.drawable.ic_log_number)
                .create()
                .show()
    }

}
