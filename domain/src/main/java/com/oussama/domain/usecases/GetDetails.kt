package com.oussama.domain.usecases

import com.oussama.domain.repositories.DetailRepository
import com.oussama.domain.repositories.detailRepository
import com.oussama.entities.Answer
import com.oussama.entities.Question
import com.oussama.entities.UserDetail
import io.reactivex.Single

const val NUMBER_ITEMS_TO_SHOW = 3

fun getUserDetails(userId: Long, repository: DetailRepository = detailRepository): Single<UserDetail> {
    return Single.zip(
        repository.getQuestionsByUserId(userId),
        repository.getFavoritesByUserId(userId),
        getAnswersWithTitle(userId),
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
    repository: DetailRepository = detailRepository
): Single<List<Answer>> {
    return repository.getAnswersByUserId(userId)
        .flatMap { mapAnswersToAnswersWithTitle(it) }
        .map { answers -> answers.filter { it.accepted } }

}

private fun mapAnswersToAnswersWithTitle(
    answers: List<Answer>,
    repository: DetailRepository = detailRepository
): Single<List<Answer>> {
    val questionsIds = answers.map { it.questionId }
    val questions = repository.getQuestionsByIds(questionsIds.joinToString(separator = ";"))

    return questions.map { createAnswer(answers, it) }
}

fun createAnswer(answers: List<Answer>, questions: List<Question>): List<Answer> {
    return answers.map {
        val question = questions.find { question -> question.questionId == it.answerId }
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

