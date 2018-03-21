package com.danielcoutts.goalsapp.sections.main

import android.os.Bundle
import com.danielcoutts.goalsapp.R
import com.danielcoutts.goalsapp.base.BaseActivity
import com.danielcoutts.goalsapp.sections.main.adapters.GoalsPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainViewModel>() {

    override val viewModelClass = MainViewModel::class

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar.title = "My Goals"
        viewPager.adapter = GoalsPagerAdapter(supportFragmentManager)
        tabs.setupWithViewPager(viewPager)
    }

    override fun subscribeToStreams() {

    }
}
