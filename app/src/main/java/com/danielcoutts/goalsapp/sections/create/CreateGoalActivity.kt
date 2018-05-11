package com.danielcoutts.goalsapp.sections.create

import android.os.Bundle
import android.support.design.widget.Snackbar
import com.danielcoutts.goalsapp.R
import com.danielcoutts.goalsapp.base.BaseActivity
import com.danielcoutts.goalsapp.etc.Recurrence
import com.pawegio.kandroid.textWatcher
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_create_goal.*


class CreateGoalActivity : BaseActivity<CreateGoalViewModel>() {
    override val viewModelClass = CreateGoalViewModel::class

    private val errorSnackbar = Snackbar.make(container, "The goal details are incomplete", Snackbar.LENGTH_SHORT)

    override fun subscribeToStreams() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_goal)

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
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeBy(
                            onSuccess = { success ->
                                if(success) finish()
                                else errorSnackbar.show()
                            },
                            onError = {

                            }
                    )
                    .addTo(compositeDisposable)
        }

        verb.textWatcher {
            onTextChanged { text, _, _, _ ->
                viewModel.verb = text.toString()
            }
        }

        number.textWatcher {
            onTextChanged { text, _, _, _ ->
                try {
                    val number = text.toString().toInt()
                    viewModel.number = number
                } catch (ignore: NumberFormatException){}
            }
        }

        noun.textWatcher {
            onTextChanged { text, _, _, _ ->
                viewModel.noun = text.toString()
            }
        }

        every.setOnCheckedChangeListener { _, id ->
            viewModel.recurrence = when(id) {
                R.id.day ->  Recurrence.DAILY
                R.id.week -> Recurrence.WEEKLY
                R.id.month -> Recurrence.MONTHLY
                else -> null
            }
        }
    }
}
