package com.oussama.domain.repositories


import com.oussama.domain.gateways.STACKOVERFLOW_API
import com.oussama.entities.UserListEntity
import io.reactivex.Single

val userRepository: UserRepository by lazy { UserRepositoryImplementer() }

interface UserRepository {
    fun getUsers(page: Int): Single<UserListEntity>
}

class UserRepositoryImplementer : UserRepository {

    override fun getUsers(page: Int): Single<UserListEntity> = STACKOVERFLOW_API.getUsers(page)

}