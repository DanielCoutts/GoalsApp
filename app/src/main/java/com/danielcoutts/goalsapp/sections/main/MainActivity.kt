package com.danielcoutts.goalsapp.sections.main

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.danielcoutts.goalsapp.R
import com.danielcoutts.goalsapp.base.BaseActivity
import com.danielcoutts.goalsapp.sections.create.CreateGoalActivity
import com.danielcoutts.goalsapp.sections.main.adapters.GoalsPagerAdapter
import com.danielcoutts.goalsapp.util.isNightTheme
import com.danielcoutts.goalsapp.util.setNavigationIconWithTint
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager.adapter = GoalsPagerAdapter(supportFragmentManager)
        tabs.setupWithViewPager(viewPager)

        addGoalButton.setOnClickListener {
            startActivity(Intent(this, CreateGoalActivity::class.java))
        }

        toolbar.setNavigationIconWithTint(this, R.drawable.ic_settings, R.color.colorIcons)
        // TODO Update
        toolbar.setNavigationOnClickListener {
            when (resources.isNightTheme()) {
                Configuration.UI_MODE_NIGHT_YES ->
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                Configuration.UI_MODE_NIGHT_NO ->
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
        }

        setNavBarLightDark(isLight = !resources.getBoolean(R.bool.isLightMode))
    }

    fun AppCompatActivity.setNavBarLightDark(isLight: Boolean) {
        var flags = window.decorView.systemUiVisibility
        flags = if (isLight) {
            flags and View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR.inv()
        } else {
            flags or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
        }
        window.decorView.systemUiVisibility = flags
    }
}
