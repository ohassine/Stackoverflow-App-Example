package com.oussama.domain.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.oussama.domain.repositories.UserRepository
import com.oussama.entities.State
import com.oussama.entities.User
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Action
import io.reactivex.schedulers.Schedulers

class UsersDataSource(
    private val compositeDisposable: CompositeDisposable,
    private val userRepository: UserRepository
) : PageKeyedDataSource<Int, User>() {

    var state: MutableLiveData<State> = MutableLiveData()
    private var retryCompletable: Completable? = null

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, User>
    ) {
        updateState(State.LOADING)
        compositeDisposable.add(userRepository.getUsers(1)
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
        compositeDisposable.add(userRepository.getUsers(params.key)
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
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
            )
        }
    }
}