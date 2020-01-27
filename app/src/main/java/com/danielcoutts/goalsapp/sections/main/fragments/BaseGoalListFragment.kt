package com.danielcoutts.goalsapp.sections.main.fragments


import android.app.AlertDialog
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.danielcoutts.goalsapp.R

import com.danielcoutts.goalsapp.base.BaseFragment
import com.danielcoutts.goalsapp.db.entities.Goal
import com.danielcoutts.goalsapp.sections.main.MainViewModel
import com.danielcoutts.goalsapp.sections.main.adapters.GoalListAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_goal_list.view.*

open class BaseGoalListFragment : BaseFragment<MainViewModel>() {
    override val viewModelClass = MainViewModel::class

    protected val adapter = GoalListAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_goal_list, container, false)

        view.recyclerView.layoutManager = LinearLayoutManager(context)
        view.recyclerView.adapter = adapter

        return view
    }

    override fun subscribeToStreams(view: View) {
        adapter.goalLogEvents
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = {
                            showLogDialogForGoal(it)
                        }
                )
                .addTo(compositeDisposable)

        adapter.goalDeleteEvents
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = {
                            showDeleteDialogForGoal(it)
                        }
                )
                .addTo(compositeDisposable)
    }

    private fun showDeleteDialogForGoal(goal: Goal) {
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

    private fun showLogDialogForGoal(goal: Goal) {
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
