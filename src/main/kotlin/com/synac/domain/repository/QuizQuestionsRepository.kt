package com.synac.domain.repository

import com.synac.domain.model.QuizQuestions
import com.synac.domain.utils.DataError
import com.synac.domain.utils.Result

interface QuizQuestionsRepository {

    suspend fun upsertQuestions(questions: QuizQuestions):Result<Unit,DataError>

    suspend fun getRandomQuestion(topicCode: Int?, limit: Int?): Result<List<QuizQuestions>,DataError>

    suspend fun getAllQuestion(topicCode: Int?): Result<List<QuizQuestions>,DataError>

    suspend fun getQuestionById(
        id: String?
    ): Result<QuizQuestions,DataError>

    suspend fun insertQuestionsInBulk(
        questions:List<QuizQuestions>
    ):Result<Unit,DataError>

    suspend fun deleteQuestionById(
        id: String?
    ): Result<Unit,DataError>


}