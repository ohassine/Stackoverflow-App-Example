package com.oussama.domain.repositories

import com.oussama.domain.gateways.STACKOVERFLOW_API
import com.oussama.domain.gateways.StackoverflowAPI
import com.oussama.entities.Answer
import com.oussama.entities.Question
import io.reactivex.Single

val detailRepository: DetailRepository by lazy { DetailRepositoryImplementer() }

class DetailRepositoryImplementer(private val stackoverflowApi : StackoverflowAPI = STACKOVERFLOW_API) : DetailRepository {
    override fun getQuestionsByUserId(userId: Long): Single<List<Question>> =
        stackoverflowApi.getQuestionsByUser(userId).map { it.items }

    override fun getAnswersByUserId(userId: Long): Single<List<Answer>> =
        stackoverflowApi.getAnswersByUser(userId).map { it.items }

    override fun getFavoritesByUserId(userId: Long): Single<List<Question>> =
        stackoverflowApi.getFavoritesByUser(userId).map { it.items }

    override fun getQuestionsByIds(ids: String): Single<List<Question>> =
        stackoverflowApi.getQuestionsById(ids).map { it.items }

}