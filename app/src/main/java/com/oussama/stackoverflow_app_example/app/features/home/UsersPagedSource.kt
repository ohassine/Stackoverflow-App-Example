package com.oussama.stackoverflow_app_example.app.features.home

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.oussama.domain.usecases.getUsers
import com.oussama.entities.State
import com.oussama.entities.User
import com.oussama.entities.UserListEntity
import com.oussama.stackoverflow_app_example.app.features.core.SchedulerProvider
import com.oussama.stackoverflow_app_example.app.features.core.scheduler
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Action

class UsersPagedSource(
    private val compositeDisposable: CompositeDisposable,
    private val getTopUsers: (Int) -> Single<UserListEntity> = { getUsers(it) },
    private val schedulerProvider: SchedulerProvider = scheduler
) : PageKeyedDataSource<Int, User>() {

    var state: MutableLiveData<State> = MutableLiveData()
    private var retryCompletable: Completable? = null

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, User>
    ) {
        updateState(State.LOADING)
        compositeDisposable.add(getTopUsers(1)
            .subscribeOn(schedulerProvider.ioScheduler())
            .subscribe({ response ->
                updateState(State.DONE)
                callback.onResult(response.items, null, 2)
            }, {
                updateState(State.ERROR)
                setRetry { loadInitial(params, callback) }
            }
            )
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, User>) {
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, User>) {
        updateState(State.LOADING)
        compositeDisposable.add(getTopUsers(params.key)
            .subscribeOn(schedulerProvider.ioScheduler())
            .subscribe({ response ->
                updateState(State.DONE)
                callback.onResult(response.items, params.key + 1)
            }, {
                updateState(State.ERROR)
                setRetry { loadAfter(params, callback) }
            }
            )
        )
    }

    private fun updateState(state: State) {
        this.state.postValue(state)
    }

    private fun setRetry(action: Action?) {
        retryCompletable = if (action == null) null else Completable.fromAction(action)
    }

    fun retry() {
        if (retryCompletable != null) {
            compositeDisposable.add(
                retryCompletable!!
                    .subscribeOn(schedulerProvider.ioScheduler())
                    .observeOn(schedulerProvider.uiScheduler())
                    .subscribe()
            )
        }
    }
}