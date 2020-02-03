package com.danielcoutts.goalsapp.sections.main.fragments

import android.os.Bundle
import android.view.View
import androidx.lifecycle.observe
import com.danielcoutts.goalsapp.sections.main.data.GoalListData

class DailyGoalListFragment : BaseGoalListFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.dayViewState().observe(owner = viewLifecycleOwner) { viewState ->
            goalListAdapter.setGoalListData(
                    GoalListData(viewState.goals, viewState.logs)
            )
        }
    }
}