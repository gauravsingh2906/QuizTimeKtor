package com.synac.data.repository

import com.mongodb.kotlin.client.coroutine.MongoDatabase
import com.synac.data.util.Constant.QUESTIONS_COLLECTION_NAME
import com.synac.domain.model.QuizQuestion
import com.synac.domain.repository.QuizQuestionRepository

class QuizQuestionRepositoryImpl(
    mongoDatabase: MongoDatabase
): QuizQuestionRepository {

    private val questionCollection = mongoDatabase
        .getCollection<QuizQuestion>(QUESTIONS_COLLECTION_NAME)

    private val quizQuestions = mutableListOf<QuizQuestion>()

    override suspend fun upsertQuestion(question: QuizQuestion) {
        questionCollection.insertOne(question)
    }

    override suspend fun getAllQuestions(topicCode: Int?, limit: Int?): List<QuizQuestion> {
        return if (topicCode != null) {
            quizQuestions
                .filter { it.topicCode == topicCode }
                .take(limit ?: quizQuestions.size)
        } else {
            quizQuestions.take(limit ?: quizQuestions.size)
        }
    }

    override suspend fun getQuestionById(id: String): QuizQuestion? {
        return quizQuestions.find { it.id == id }
    }

    override suspend fun deleteQuestionById(id: String): Boolean {
        return quizQuestions.removeIf { it.id == id }
    }
}