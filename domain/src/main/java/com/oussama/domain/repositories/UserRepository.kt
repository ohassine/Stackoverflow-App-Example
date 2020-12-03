package com.oussama.domain.repositories


import com.oussama.entities.UserListModel
import io.reactivex.Single

interface UserRepository {
    fun getUsers(page: Int): Single<UserListModel>
}