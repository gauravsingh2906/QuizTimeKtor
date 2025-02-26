package com.synac.domain.repository

import com.synac.domain.model.QuizQuestion
import com.synac.domain.util.DataError
import com.synac.domain.util.Result

interface QuizQuestionRepository {

    suspend fun getAllQuestions(topicCode: Int?): Result<List<QuizQuestion>, DataError>

    suspend fun getRandomQuestions(topicCode: Int?, limit: Int?): Result<List<QuizQuestion>, DataError>

    suspend fun upsertQuestion(question: QuizQuestion): Result<Unit, DataError>

    suspend fun insertQuestionsInBulk(questions: List<QuizQuestion>): Result<Unit, DataError>

    suspend fun getQuestionById(id: String?): Result<QuizQuestion, DataError>

    suspend fun deleteQuestionById(id: String?): Result<Unit, DataError>

}