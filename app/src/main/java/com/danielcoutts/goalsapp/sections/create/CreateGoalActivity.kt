package com.danielcoutts.goalsapp.sections.create

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.danielcoutts.goalsapp.R
import com.danielcoutts.goalsapp.base.BaseActivity

class CreateGoalActivity : BaseActivity<CreateGoalViewModel>() {
    override val viewModelClass = CreateGoalViewModel::class

    override fun subscribeToStreams() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_goal)
    }
}
