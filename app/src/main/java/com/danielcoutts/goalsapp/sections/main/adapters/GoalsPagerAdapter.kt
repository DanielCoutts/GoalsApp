package com.danielcoutts.goalsapp.sections.main.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.danielcoutts.goalsapp.sections.main.fragments.GoalListFragment

class GoalsPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    private val pageTitles = listOf("Daily", "Weekly", "Monthly")
    private val fragments = listOf(GoalListFragment(), Fragment(), Fragment())

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