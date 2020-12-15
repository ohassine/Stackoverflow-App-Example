package com.oussama.stackoverflow_app_example.app.features.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oussama.domain.usecases.getUserDetails
import com.oussama.entities.State
import com.oussama.entities.UserDetail
import com.oussama.stackoverflow_app_example.app.features.core.SchedulerProvider
import com.oussama.stackoverflow_app_example.app.features.core.scheduler
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

class DetailViewModel(
    private val getCompleteDetails: (Long) -> Single<UserDetail> = { getUserDetails(it) },
    private var compositeDisposable: CompositeDisposable = CompositeDisposable(),
    var details: MutableLiveData<MutableList<Any>> = MutableLiveData(),
    private val schedulerProvider: SchedulerProvider = scheduler
) : ViewModel() {

    init {
        details.value = ArrayList()
    }

    fun addItem(item: Any) {
        details.value?.add(item)
        details.value = details.value
    }

    fun getDetails(userId: Long) {
        compositeDisposable.add(
            getCompleteDetails(userId)
                .subscribeOn(schedulerProvider.ioScheduler())
                .observeOn(schedulerProvider.uiScheduler())
                .subscribe({
                    details.value?.remove(State.LOADING)
                    addList(it)
                    details.value = details.value
                }, {})
        )
    }

    private fun addList(list: UserDetail) {
        if (list.answers.isNotEmpty()) {
            details.value?.add("Answers")
            details.value?.addAll(list.answers)
        }
        if (list.questions.isNotEmpty()) {
            details.value?.add("Questions")
            details.value?.addAll(list.questions)
        }
        if (list.favorites.isNotEmpty()) {
            details.value?.add("Favorites")
            details.value?.addAll(list.favorites)
        }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}