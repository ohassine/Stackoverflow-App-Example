package com.oussama.domain.repositories


import com.oussama.domain.gateways.STACKOVERFLOW_API
import com.oussama.domain.gateways.StackoverflowAPI
import com.oussama.entities.UserListEntity
import io.reactivex.Single

val userRepository: UserRepository by lazy { UserRepositoryImplementer() }

interface UserRepository {
    fun getUsers(page: Int): Single<UserListEntity>
}

class UserRepositoryImplementer(private val stackoverflowApi : StackoverflowAPI = STACKOVERFLOW_API)  : UserRepository {

    override fun getUsers(page: Int): Single<UserListEntity> = stackoverflowApi.getUsers(page)

}