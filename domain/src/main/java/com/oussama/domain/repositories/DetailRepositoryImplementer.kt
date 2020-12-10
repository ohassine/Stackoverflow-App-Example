package com.oussama.domain.repositories

import com.oussama.domain.gateways.STACKOVERFLOW_API
import com.oussama.entities.AnswerListEntity
import com.oussama.entities.QuestionListEntity
import io.reactivex.Single

val detailRepository: DetailRepository by lazy { DetailRepositoryImplementer() }

class DetailRepositoryImplementer : DetailRepository {
    override fun getQuestionsByUserId(userId: Long): Single<QuestionListEntity> =
        STACKOVERFLOW_API.getQuestionsByUser(userId)

    override fun getAnswersByUserId(userId: Long): Single<AnswerListEntity> =
        STACKOVERFLOW_API.getAnswersByUser(userId)

    override fun getFavoritesByUserId(userId: Long): Single<QuestionListEntity> =
        STACKOVERFLOW_API.getFavoritesByUser(userId)

    override fun getQuestionsByIds(ids: String): Single<QuestionListEntity> =
        STACKOVERFLOW_API.getQuestionsById(ids)

}