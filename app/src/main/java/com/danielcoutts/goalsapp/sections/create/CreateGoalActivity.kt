package com.danielcoutts.goalsapp.sections.create

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.danielcoutts.goalsapp.R
import com.danielcoutts.goalsapp.base.BaseActivity
import com.pawegio.kandroid.textWatcher
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_create_goal.*

class CreateGoalActivity : BaseActivity<CreateGoalViewModel>() {
    override val viewModelClass = CreateGoalViewModel::class

    override fun subscribeToStreams() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_goal)

        toolbar.setNavigationIcon(R.drawable.ic_cancel)
        toolbar.setNavigationOnClickListener {
            finish()
        }

        cancelButton.setOnClickListener {
            finish()
        }

        okButton.setOnClickListener {
            // TODO saveEvent
            viewModel.createGoal()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeBy(
                            onComplete = {
                                finish()
                            },
                            onError = {

                            }
                    )
                    .addTo(compositeDisposable)
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

        every.setOnCheckedChangeListener { _, id ->
            when(id) {
                R.id.day -> {}
                R.id.week -> {}
                R.id.month -> {}
            }
        }
    }
}
