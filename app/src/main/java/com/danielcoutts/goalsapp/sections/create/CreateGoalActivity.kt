package com.danielcoutts.goalsapp.sections.create

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.activity.viewModels
import com.google.android.material.snackbar.Snackbar
import com.danielcoutts.goalsapp.R
import com.danielcoutts.goalsapp.base.BaseActivity
import com.danielcoutts.goalsapp.data.models.Recurrence
import com.danielcoutts.goalsapp.util.observeEvents
import kotlinx.android.synthetic.main.activity_create_goal.*

class CreateGoalActivity : BaseActivity() {

    private val viewModel: CreateGoalViewModel by viewModels()

    private lateinit var errorSnackbar: Snackbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_goal)

        errorSnackbar = Snackbar.make(container, "The goal details are incomplete", Snackbar.LENGTH_SHORT)

        verb.requestFocus()

        toolbar.setNavigationIcon(R.drawable.ic_cancel)
        toolbar.setNavigationOnClickListener {
            finish()
        }

        cancelButton.setOnClickListener {
            finish()
        }

        okButton.setOnClickListener {
            viewModel.createGoal()
        }

        viewModel.events.observeEvents(this) {
            when (it) {
                CreateGoalViewModel.CreateGoalEvent.GOAL_CREATED -> finish()
                CreateGoalViewModel.CreateGoalEvent.GOAL_CREATION_ERROR -> errorSnackbar.show()
            }
        }

        verb.addTextWatcher(
                onTextChanged = { text, _, _, _ ->
                    viewModel.verb = text.toString()
                }
        )

        number.addTextWatcher(
                onTextChanged = { text, _, _, _ ->
                    try {
                        val number = text.toString().toInt()
                        viewModel.number = number
                    } catch (ignore: NumberFormatException) {
                    }
                }
        )

        noun.addTextWatcher(
                onTextChanged = { text, _, _, _ ->
                    viewModel.noun = text.toString()
                }
        )

        every.setOnCheckedChangeListener { _, id ->
            viewModel.recurrence = when (id) {
                R.id.day -> Recurrence.DAILY
                R.id.week -> Recurrence.WEEKLY
                R.id.month -> Recurrence.MONTHLY
                else -> null
            }
        }
    }

    private fun EditText.addTextWatcher(
            afterTextChanged: ((s: Editable?) -> Unit)? = null,
            beforeTextChanged: ((s: CharSequence?, start: Int, count: Int, after: Int) -> Unit)? = null,
            onTextChanged: ((s: CharSequence?, start: Int, end: Int, count: Int) -> Unit)? = null
    ) {
        addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                afterTextChanged?.invoke(s)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                beforeTextChanged?.invoke(s, start, count, after)
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                onTextChanged?.invoke(s, start, before, count)
            }
        })
    }
}
