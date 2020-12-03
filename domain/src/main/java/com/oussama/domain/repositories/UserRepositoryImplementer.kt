package com.oussama.domain.repositories

import com.oussama.domain.gateways.STACKOVERFLOW_API
import com.oussama.entities.UserListModel
import io.reactivex.Single

val userRepository: UserRepository by lazy { UserRepositoryImplementer() }

class UserRepositoryImplementer : UserRepository {

    override fun getUsers(page: Int): Single<UserListModel> = STACKOVERFLOW_API.getUsers(page)

}