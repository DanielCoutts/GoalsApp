package com.danielcoutts.goalsapp.sections.main.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.danielcoutts.goalsapp.sections.main.fragments.DailyGoalListFragment
import com.danielcoutts.goalsapp.sections.main.fragments.MonthlyGoalListFragment
import com.danielcoutts.goalsapp.sections.main.fragments.WeeklyGoalListFragment

class GoalsPagerAdapter(fragmentManager: FragmentManager)
    : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val pageTitles = listOf("Daily", "Weekly", "Monthly")
    private val fragments = listOf(DailyGoalListFragment(), WeeklyGoalListFragment(), MonthlyGoalListFragment())

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return pageTitles[position]
    }
}