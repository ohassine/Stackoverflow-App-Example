package com.oussama.domain.repositories

import com.oussama.entities.Answer
import com.oussama.entities.Question
import io.reactivex.Single

interface DetailRepository {
    fun getQuestionsByUserId(userId: Long): Single<List<Question>>
    fun getAnswersByUserId(userId: Long): Single<List<Answer>>
    fun getFavoritesByUserId(userId: Long): Single<List<Question>>
    fun getQuestionsByIds(ids: String): Single<List<Question>>
}