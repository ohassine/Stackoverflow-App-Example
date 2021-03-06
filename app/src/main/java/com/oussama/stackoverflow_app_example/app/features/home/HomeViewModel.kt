package com.oussama.stackoverflow_app_example.app.features.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.oussama.entities.State
import com.oussama.entities.User
import io.reactivex.disposables.CompositeDisposable

class HomeViewModel(
    var users: LiveData<PagedList<User>> = MutableLiveData(),
    private var disposable: CompositeDisposable = CompositeDisposable(),
    private val usersPagedSourceFactory: UsersPagedSourceFactory = UsersPagedSourceFactory(
        disposable
    )
) : ViewModel() {

    private val pageSize = 5

    init {
        val config = PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setInitialLoadSizeHint(pageSize * 2)
            .setEnablePlaceholders(false)
            .build()
        users = LivePagedListBuilder(usersPagedSourceFactory, config).build()
    }

    fun getState(): LiveData<State> = Transformations.switchMap(
        usersPagedSourceFactory.newsDataSourceLiveData,
        UsersPagedSource::state
    )

    fun retry() {
        usersPagedSourceFactory.newsDataSourceLiveData.value?.retry()
    }

    fun isListEmpty(): Boolean {
        return users.value?.isEmpty() ?: true
    }

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }
}