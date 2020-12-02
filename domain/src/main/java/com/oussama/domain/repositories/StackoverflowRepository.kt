package com.oussama.domain.repositories


import com.oussama.entities.UserListModel
import io.reactivex.Single

interface StackoverflowRepository {
    fun getUsers(page : Int) : Single<UserListModel>
}