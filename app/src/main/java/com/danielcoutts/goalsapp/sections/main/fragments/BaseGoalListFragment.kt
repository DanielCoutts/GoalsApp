package com.danielcoutts.goalsapp.sections.main.fragments


import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.danielcoutts.goalsapp.base.BaseFragment
import com.danielcoutts.goalsapp.etc.Recurrence
import com.danielcoutts.goalsapp.sections.main.MainViewModel
import com.danielcoutts.goalsapp.sections.main.adapters.GoalListAdapter

abstract class BaseGoalListFragment(private val recurrence: Recurrence) : BaseFragment<MainViewModel>() {
    override val viewModelClass = MainViewModel::class

    protected val adapter = GoalListAdapter(recurrence)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = RecyclerView(context)
        view.layoutManager = LinearLayoutManager(context)
        view.adapter = adapter

        return view
    }

}
