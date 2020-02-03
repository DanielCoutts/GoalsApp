package com.danielcoutts.goalsapp.sections.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.danielcoutts.goalsapp.R
import com.danielcoutts.goalsapp.base.BaseActivity
import com.danielcoutts.goalsapp.sections.create.CreateGoalActivity
import com.danielcoutts.goalsapp.sections.main.adapters.GoalsPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar.title = "My Goals"
        viewPager.adapter = GoalsPagerAdapter(supportFragmentManager)
        tabs.setupWithViewPager(viewPager)

        addGoalButton.setOnClickListener {
            startActivity(Intent(this, CreateGoalActivity::class.java))
        }
    }
}
