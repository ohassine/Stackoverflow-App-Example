package com.oussama.stackoverflow_app_example.app.features.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.oussama.domain.datasource.UsersDataSource
import com.oussama.domain.datasource.UsersDataSourceFactory
import com.oussama.entities.State
import com.oussama.entities.User
import io.reactivex.disposables.CompositeDisposable

class HomeViewModel : ViewModel() {


    var users: LiveData<PagedList<User>> = MutableLiveData()
    var disposable = CompositeDisposable()
    private val pageSize = 5

    private val usersDataSourceFactory: UsersDataSourceFactory =
        UsersDataSourceFactory(disposable)

    init {
        val config = PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setInitialLoadSizeHint(pageSize * 2)
            .setEnablePlaceholders(false)
            .build()
        users = LivePagedListBuilder(usersDataSourceFactory, config).build()
    }

    fun getState(): LiveData<State> = Transformations.switchMap(
        usersDataSourceFactory.newsDataSourceLiveData,
        UsersDataSource::state
    )

    fun retry() {
        usersDataSourceFactory.newsDataSourceLiveData.value?.retry()
    }

    fun isListEmpty(): Boolean {
        return users.value?.isEmpty() ?: true
    }

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }
}