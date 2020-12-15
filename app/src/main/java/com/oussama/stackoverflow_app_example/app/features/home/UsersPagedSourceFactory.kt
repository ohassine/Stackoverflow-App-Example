package com.oussama.stackoverflow_app_example.app.features.home

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.oussama.entities.User
import io.reactivex.disposables.CompositeDisposable

class UsersPagedSourceFactory(
    private val compositeDisposable: CompositeDisposable) : DataSource.Factory<Int, User>() {

    val newsDataSourceLiveData = MutableLiveData<UsersPagedSource>()

    override fun create(): DataSource<Int, User> {
        val usersDataSource = UsersPagedSource(compositeDisposable)
        newsDataSourceLiveData.postValue(usersDataSource)
        return usersDataSource

    }
}