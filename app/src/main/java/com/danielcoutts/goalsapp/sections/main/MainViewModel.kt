package com.danielcoutts.goalsapp.sections.main

import com.danielcoutts.goalsapp.base.BaseViewModel
import com.danielcoutts.goalsapp.sections.main.data.ViewState
import io.reactivex.Observable
import io.reactivex.rxkotlin.Observables
import io.reactivex.subjects.BehaviorSubject

class MainViewModel : BaseViewModel() {

    private val myStringSubject = BehaviorSubject.create<String>()
    private val myStringSubject2 = BehaviorSubject.create<String>()

    val model = MainModel()

    override fun onCleared() {
        super.onCleared()
    }

    fun viewState(): Observable<ViewState> {
        return Observables.combineLatest(myStringSubject, myStringSubject2) {
            s1, s2 -> ViewState(s1, s2)
        }
    }

    fun updateString(myString: String) {
        myStringSubject.onNext(myString)
    }

}