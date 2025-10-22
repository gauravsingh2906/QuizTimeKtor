package com.synac.data.repository

import com.mongodb.client.model.Aggregates
import com.mongodb.client.model.Filters
import com.mongodb.client.model.Updates
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import com.synac.data.database.entity.QuizQuestionEntity
import com.synac.data.mapper.toQuizQuestion
import com.synac.data.mapper.toQuizQuestionEntity
import com.synac.domain.model.QuizQuestions
import com.synac.domain.repository.QuizQuestionsRepository
import com.synac.domain.utils.DataError
import com.synac.domain.utils.Result
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import org.bson.conversions.Bson

class QuizQuestionRepositoryImpl(
    mongoDatabase: MongoDatabase
) : QuizQuestionsRepository {

    private val questionCollection = mongoDatabase
        .getCollection<QuizQuestionEntity>("quiz_questions")


    override suspend fun upsertQuestions(
        questions: QuizQuestions
    ): Result<Unit, DataError> {
        return try {
            if (questions.id == null) {
                questionCollection.insertOne(questions.toQuizQuestionEntity())
            } else {
                val filterQuery = Filters.eq(
                    QuizQuestionEntity::_id.name, questions.id
                )
                val updateQuery = Updates.combine(
                    Updates.set(QuizQuestionEntity::question.name, questions.question),
                    Updates.set(QuizQuestionEntity::correctAnswer.name, questions.correctAnswer),
                    Updates.set(QuizQuestionEntity::incorrectAnswer.name, questions.incorrectAnswer),
                    Updates.set(QuizQuestionEntity::explanation.name, questions.explanation),
                    Updates.set(QuizQuestionEntity::topicCode.name, questions.topicCode)
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

    override suspend fun getRandomQuestion(topicCode: Int?, limit: Int?): Result<List<QuizQuestions>, DataError> {
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
                .map { it.toQuizQuestion() }.toList()

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

    override suspend fun getAllQuestion(topicCode: Int?): Result<List<QuizQuestions>, DataError> {
        return try {
            val filterQuery = topicCode?.let {
                Filters.eq(QuizQuestionEntity::topicCode.name, it)
            } ?: Filters.empty()
            val questions =
                questionCollection.find(filter = filterQuery).map { it.toQuizQuestion() }.toList()

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
    ): Result<QuizQuestions, DataError> {
        if (id.isNullOrBlank()) {
            Result.Failure(DataError.Validation)
        }
        return try {
            val filterQuery = Filters.eq(
                QuizQuestionEntity::_id.name, id
            )
            val questionEntity = questionCollection
                .find(filterQuery)
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

    override suspend fun insertQuestionsInBulk(questions: List<QuizQuestions>): Result<Unit, DataError> {
        return try {
            val questionEntity = questions.map { it.toQuizQuestionEntity() }
            questionCollection.insertMany(questionEntity)
            Result.Success(Unit)

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
            val deleteResult = questionCollection.deleteOne(filter = filterQuery)
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