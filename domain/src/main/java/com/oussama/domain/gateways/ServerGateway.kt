package com.oussama.domain.gateways

import com.oussama.entities.UserListModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

val STACKOVERFLOW_API : StackoverflowAPI by lazy {
    retrofit.create(StackoverflowAPI::class.java)
}

interface StackoverflowAPI {

    @GET("/users?order=desc&sort=reputation&site=stackoverflow")
    fun getUsers(@Query("page") page: Int = 1): Single<UserListModel>

}