package com.oussama.domain.repositories

import com.oussama.entities.AnswerListEntity
import com.oussama.entities.QuestionListEntity
import io.reactivex.Single

interface DetailRepository {
    fun getQuestionsByUserId(userId: Long): Single<QuestionListEntity>
    fun getAnswersByUserId(userId: Long): Single<AnswerListEntity>
    fun getFavoritesByUserId(userId: Long): Single<QuestionListEntity>
    fun getQuestionsByIds(ids: String): Single<QuestionListEntity>
}