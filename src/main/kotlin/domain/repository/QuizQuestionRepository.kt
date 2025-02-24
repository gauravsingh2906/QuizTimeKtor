package com.synac.domain.repository

import com.synac.domain.model.QuizQuestion

interface QuizQuestionRepository {
    suspend fun upsertQuestion(question: QuizQuestion)
    suspend fun getAllQuestions(topicCode: Int?, limit: Int?): List<QuizQuestion>
    suspend fun getQuestionById(id: String): QuizQuestion?
    suspend fun deleteQuestionById(id: String): Boolean
}