package com.oussama.stackoverflow_app_example.app.features.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oussama.domain.usecases.getUsersUseCase
import com.oussama.entities.User
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class HomeViewModel : ViewModel() {

    private val TAG = "HomeViewModel"

    var users: MutableLiveData<List<User>> = MutableLiveData()
    lateinit var disposable: Disposable

    fun getUsers(page: Int) {

        disposable = getUsersUseCase(page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ list ->
                users.value = list.items
            },
                { e ->
                    Log.d(TAG, "onCreate: " + e)
                }
            )
    }

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }
}