package com.oussama.domain.usecases

import com.oussama.domain.repositories.stackoverflowRepository
import com.oussama.entities.UserListModel
import io.reactivex.Single

fun getUsersUseCase(page: Int) : Single<UserListModel> = stackoverflowRepository.getUsers(page)