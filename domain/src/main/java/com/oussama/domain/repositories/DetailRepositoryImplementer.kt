package com.oussama.domain.repositories

import com.oussama.domain.gateways.STACKOVERFLOW_API
import com.oussama.entities.Answer
import com.oussama.entities.Question
import io.reactivex.Single

val detailRepository: DetailRepository by lazy { DetailRepositoryImplementer() }

class DetailRepositoryImplementer : DetailRepository {
    override fun getQuestionsByUserId(userId: Long): Single<List<Question>> =
        STACKOVERFLOW_API.getQuestionsByUser(userId).map { it.items }

    override fun getAnswersByUserId(userId: Long): Single<List<Answer>> =
        STACKOVERFLOW_API.getAnswersByUser(userId).map { it.items }

    override fun getFavoritesByUserId(userId: Long): Single<List<Question>> =
        STACKOVERFLOW_API.getFavoritesByUser(userId).map { it.items }

    override fun getQuestionsByIds(ids: String): Single<List<Question>> =
        STACKOVERFLOW_API.getQuestionsById(ids).map { it.items }

}