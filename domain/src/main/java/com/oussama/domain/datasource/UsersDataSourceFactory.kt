package com.oussama.domain.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.oussama.entities.User
import io.reactivex.disposables.CompositeDisposable

class UsersDataSourceFactory(
    private val compositeDisposable: CompositeDisposable) : DataSource.Factory<Int, User>() {

    val newsDataSourceLiveData = MutableLiveData<UsersDataSource>()

    override fun create(): DataSource<Int, User> {
        val usersDataSource = UsersDataSource(compositeDisposable)
        newsDataSourceLiveData.postValue(usersDataSource)
        return usersDataSource

    }
}