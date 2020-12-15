package com.oussama.domain.usecases

import com.oussama.domain.repositories.UserRepository
import com.oussama.domain.repositories.userRepository
import com.oussama.entities.UserListEntity
import io.reactivex.Single

fun getUsers(page: Int, repository: UserRepository = userRepository): Single<UserListEntity> =
    repository.getUsers(page)
