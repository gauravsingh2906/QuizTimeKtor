package com.synac.data.repository

import com.mongodb.client.model.Aggregates
import com.mongodb.client.model.Filters
import com.mongodb.client.model.Updates
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import com.synac.data.database.entity.QuizQuestionEntity
import com.synac.data.mapper.toQuizQuestion
import com.synac.data.mapper.toQuizQuestionEntity
import com.synac.data.util.Constant.QUESTIONS_COLLECTION_NAME
import com.synac.domain.model.QuizQuestion
import com.synac.domain.repository.QuizQuestionRepository
import com.synac.domain.util.DataError
import com.synac.domain.util.Result
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import org.bson.conversions.Bson

class QuizQuestionRepositoryImpl(
    mongoDatabase: MongoDatabase
) : QuizQuestionRepository {

    private val questionCollection = mongoDatabase
        .getCollection<QuizQuestionEntity>(QUESTIONS_COLLECTION_NAME)

    override suspend fun upsertQuestion(
        question: QuizQuestion
    ): Result<Unit, DataError> {
        return try {
            if (question.id == null) {
                questionCollection.insertOne(question.toQuizQuestionEntity())
            } else {
                val filterQuery = Filters.eq(
                    QuizQuestionEntity::_id.name, question.id
                )
                val updateQuery = Updates.combine(
                    Updates.set(QuizQuestionEntity::question.name, question.question),
                    Updates.set(QuizQuestionEntity::correctAnswer.name, question.correctAnswer),
                    Updates.set(QuizQuestionEntity::incorrectAnswers.name, question.incorrectAnswers),
                    Updates.set(QuizQuestionEntity::explanation.name, question.explanation),
                    Updates.set(QuizQuestionEntity::topicCode.name, question.topicCode)
                )
                val updateResult = questionCollection.updateOne(filterQuery, updateQuery)
                if (updateResult.modifiedCount == 0L) {
                    return Result.Failure(DataError.NotFound)
                }
            }
            Result.Success(Unit)
        } catch (e: Exception) {
            e.printStackTrace()
            Result.Failure(DataError.Database)
        }
    }

    override suspend fun insertQuestionsInBulk(questions: List<QuizQuestion>): Result<Unit, DataError> {
        return try {
            val questionsEntity = questions.map { it.toQuizQuestionEntity() }
            questionCollection.insertMany(questionsEntity)
            Result.Success(Unit)
        } catch (e: Exception) {
            e.printStackTrace()
            Result.Failure(DataError.Database)
        }
    }

    override suspend fun getAllQuestions(
        topicCode: Int?
    ): Result<List<QuizQuestion>, DataError> {
        return try {
            val filterQuery = topicCode?.let {
                Filters.eq(QuizQuestionEntity::topicCode.name, it)
            } ?: Filters.empty()

            val questions = questionCollection
                .find(filter = filterQuery)
                .map { it.toQuizQuestion() }
                .toList()

            if (questions.isNotEmpty()) {
                Result.Success(questions)
            } else {
                Result.Failure(DataError.NotFound)
            }

        } catch (e: Exception) {
            e.printStackTrace()
            Result.Failure(DataError.Database)
        }
    }

    override suspend fun getRandomQuestions(
        topicCode: Int?,
        limit: Int?
    ): Result<List<QuizQuestion>, DataError> {
        return try {
            val questionLimit = limit?.takeIf { it > 0 } ?: 10
            val filterQuery = Filters.eq(
                QuizQuestionEntity::topicCode.name, topicCode
            )
            val matchStage = if (topicCode == null || topicCode == 0) {
                emptyList<Bson>()
            } else {
                listOf(Aggregates.match(filterQuery))
            }

            val pipeline = matchStage + Aggregates.sample(questionLimit)

            val questions = questionCollection
                .aggregate(pipeline)
                .map { it.toQuizQuestion() }
                .toList()

            if (questions.isNotEmpty()) {
                Result.Success(questions)
            } else {
                Result.Failure(DataError.NotFound)
            }

        } catch (e: Exception) {
            e.printStackTrace()
            Result.Failure(DataError.Database)
        }
    }

    override suspend fun getQuestionById(
        id: String?
    ): Result<QuizQuestion, DataError> {
        if (id.isNullOrBlank()) {
            return Result.Failure(DataError.Validation)
        }
        return try {
            val filterQuery = Filters.eq(
                QuizQuestionEntity::_id.name, id
            )
            val questionEntity = questionCollection
                .find(filter = filterQuery)
                .firstOrNull()

            if (questionEntity != null) {
                val question = questionEntity.toQuizQuestion()
                Result.Success(question)
            } else {
                Result.Failure(DataError.NotFound)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Result.Failure(DataError.Database)
        }
    }

    override suspend fun deleteQuestionById(
        id: String?
    ): Result<Unit, DataError> {
        if (id.isNullOrBlank()) {
            return Result.Failure(DataError.Validation)
        }
        return try {
            val filterQuery = Filters.eq(
                QuizQuestionEntity::_id.name, id
            )
            val deleteResult = questionCollection
                .deleteOne(filter = filterQuery)
            if (deleteResult.deletedCount > 0) {
                Result.Success(Unit)
            } else {
                Result.Failure(DataError.NotFound)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Result.Failure(DataError.Database)
        }
    }
}