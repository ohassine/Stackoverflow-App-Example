package com.oussama.domain.usecases

import com.oussama.domain.repositories.DetailRepository
import com.oussama.entities.Answer
import com.oussama.entities.Question
import com.oussama.entities.UserDetail
import io.reactivex.Single

const val NUMBER_ITEMS_TO_SHOW = 3

fun getUserDetails(userId: Long, detailRepository: DetailRepository): Single<UserDetail> {
    return Single.zip(
        detailRepository.getQuestionsByUserId(userId),
        detailRepository.getFavoritesByUserId(userId),
        getAnswersWithTitle(userId, detailRepository),
        { questions, favorites, answers ->
            UserDetail(
                questions.take(NUMBER_ITEMS_TO_SHOW),
                answers.take(NUMBER_ITEMS_TO_SHOW),
                favorites.take(NUMBER_ITEMS_TO_SHOW)
            )
        }
    )
}

private fun getAnswersWithTitle(
    userId: Long,
    detailRepository: DetailRepository
): Single<List<Answer>> {
    return detailRepository.getAnswersByUserId(userId)
        .flatMap { mapAnswersToAnswersWithTitle(it, detailRepository) }
        .map { answers -> answers.filter { it.accepted } }

}

private fun mapAnswersToAnswersWithTitle(
    answers: List<Answer>,
    detailRepository: DetailRepository
): Single<List<Answer>> {
    val questionsIds = answers.map { it.questionId }
    val questions = detailRepository.getQuestionsByIds(questionsIds.joinToString(separator = ";"))

    return questions.map { createAnswer(answers, it) }
}

private fun createAnswer(answers: List<Answer>, questions: List<Question>): List<Answer> {
    return answers.map {
        val question = questions.find { question -> question.questionId == it.questionId }
        Answer(
            it.answerId,
            it.questionId,
            it.score,
            it.accepted,
            it.owner,
            question?.title ?: "Unknown"
        )
    }
}

