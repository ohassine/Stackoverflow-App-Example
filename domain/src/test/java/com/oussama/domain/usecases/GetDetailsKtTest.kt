package com.oussama.domain.usecases

import com.oussama.entities.Answer
import com.oussama.entities.Question
import org.junit.Assert
import org.junit.Test


class GetDetailsKtTest{

    @Test
    fun `createAnswer() with empty answers then return empty list`(){
        val answers = listOf<Answer>()
        val questions = listOf(Question(), Question())

        val result = createAnswer(answers, questions)

        Assert.assertEquals(0, result.size)
    }

    @Test
    fun `createAnswer() with empty questions then return answers list`(){
        val answers = listOf(Answer(), Answer())
        val questions = listOf<Question>()

        val result = createAnswer(answers, questions)

        Assert.assertEquals(2, result.size)
    }

    @Test
    fun `createAnswer() with empty answers and questions then return empty list`(){
        val answers = listOf<Answer>()
        val questions = listOf<Question>()

        val result = createAnswer(answers, questions)

        Assert.assertEquals(0, result.size)
    }

    @Test
    fun `createAnswer() with non matching Ids then return list of answers with "Unknown" title`(){
        val answers = listOf(Answer(answerId = 55))
        val questions = listOf(Question(questionId = 44))

        val result = createAnswer(answers, questions)

        Assert.assertEquals("Unknown", result[0].questionTitle)
    }

    @Test
    fun `createAnswer() with matching Ids then return list of answers with the valid title`(){
        val question = "How to declare lazy value using Kotlin"
        val answers = listOf(Answer(answerId = 88, questionId = 44))
        val questions = listOf(Question(questionId = 44, title = question))

        val result = createAnswer(answers, questions)

        Assert.assertEquals(question, result[0].questionTitle)
    }

}