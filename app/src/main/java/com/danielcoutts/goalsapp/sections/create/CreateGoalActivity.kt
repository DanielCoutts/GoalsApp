package com.danielcoutts.goalsapp.sections.create

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.danielcoutts.goalsapp.R
import com.danielcoutts.goalsapp.base.BaseActivity
import com.pawegio.kandroid.textWatcher
import kotlinx.android.synthetic.main.activity_create_goal.*

class CreateGoalActivity : BaseActivity<CreateGoalViewModel>() {
    override val viewModelClass = CreateGoalViewModel::class

    override fun subscribeToStreams() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_goal)

        val spinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.recurrence_options, android.R.layout.simple_spinner_item)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        recurrence.adapter = spinnerAdapter;

        toolbar.setNavigationIcon(R.drawable.ic_cancel)
        toolbar.setNavigationOnClickListener {
            finish()
        }

        cancelButton.setOnClickListener {
            finish()
        }

        okButton.setOnClickListener {
            // TODO saveEvent
            finish()
        }

        verb.textWatcher {
            onTextChanged { text, _, _, _ ->

            }
        }

        number.textWatcher {
            onTextChanged { text, _, _, _ ->

            }
        }

        noun.textWatcher {
            onTextChanged { text, _, _, _ ->

            }
        }

        recurrence.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) = Unit

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

            }

        }
    }
}
