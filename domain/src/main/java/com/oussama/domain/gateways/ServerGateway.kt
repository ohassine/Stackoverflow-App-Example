package com.oussama.domain.gateways

import com.oussama.entities.AnswerListEntity
import com.oussama.entities.QuestionListEntity
import com.oussama.entities.UserListEntity
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

val STACKOVERFLOW_API: StackoverflowAPI by lazy {
    retrofit.create(StackoverflowAPI::class.java)
}

interface StackoverflowAPI {

    @GET("/users?order=desc&sort=reputation&site=stackoverflow")
    fun getUsers(@Query("page") page: Int = 1): Single<UserListEntity>

    @GET("/users/{userId}/questions?order=desc&sort=votes&site=stackoverflow")
    fun getQuestionsByUser(@Path("userId") userId: Long): Single<QuestionListEntity>

    @GET("/users/{userId}/favorites?order=desc&sort=votes&site=stackoverflow")
    fun getFavoritesByUser(@Path("userId") userId: Long): Single<QuestionListEntity>

    @GET("/users/{userId}/answers?order=desc&sort=votes&site=stackoverflow")
    fun getAnswersByUser(@Path("userId") userId: Long): Single<AnswerListEntity>

    @GET("/questions/{questionIds}?order=desc&sort=activity&site=stackoverflow")
    fun getQuestionsById(@Path("questionIds") questionId: String): Single<QuestionListEntity>
}