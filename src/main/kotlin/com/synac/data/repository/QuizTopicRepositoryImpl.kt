package com.synac.data.repository

import com.mongodb.client.model.Filters
import com.mongodb.client.model.Sorts
import com.mongodb.client.model.Updates
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import com.synac.data.database.entity.QuizTopicEntity
import com.synac.data.mapper.toQuizTopic
import com.synac.data.mapper.toQuizTopicEntity
import com.synac.data.util.Constant.TOPICS_COLLECTION_NAME
import com.synac.domain.model.QuizTopic
import com.synac.domain.repository.QuizTopicRepository
import com.synac.domain.util.DataError
import com.synac.domain.util.Result
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList

class QuizTopicRepositoryImpl(
    mongoDatabase: MongoDatabase
) : QuizTopicRepository {

    private val topicsCollection = mongoDatabase
        .getCollection<QuizTopicEntity>(TOPICS_COLLECTION_NAME)

    override suspend fun getAllTopics(): Result<List<QuizTopic>, DataError> {
        return try {
            val sortQuery = Sorts.ascending(
                QuizTopicEntity::code.name
            )
            val topics = topicsCollection
                .find()
                .sort(sortQuery)
                .map { it.toQuizTopic() }
                .toList()
            if (topics.isNotEmpty()) {
                Result.Success(topics)
            } else {
                Result.Failure(DataError.NotFound)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Result.Failure(DataError.Database)
        }
    }

    override suspend fun upsertTopic(topic: QuizTopic): Result<Unit, DataError> {
        return try {
            if (topic.id == null) {
                topicsCollection.insertOne(topic.toQuizTopicEntity())
            } else {
                val filterQuery = Filters.eq(
                    QuizTopicEntity::_id.name, topic.id
                )
                val updateQuery = Updates.combine(
                    Updates.set(QuizTopicEntity::name.name, topic.name),
                    Updates.set(QuizTopicEntity::imageUrl.name, topic.imageUrl),
                    Updates.set(QuizTopicEntity::code.name, topic.code)
                )
                val updateResult = topicsCollection.updateOne(filterQuery, updateQuery)
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

    override suspend fun getTopicById(id: String?): Result<QuizTopic, DataError> {
        if (id.isNullOrBlank()) {
            return Result.Failure(DataError.Validation)
        }
        return try {
            val filterQuery = Filters.eq(
                QuizTopicEntity::_id.name, id
            )
            val topicEntity = topicsCollection
                .find(filter = filterQuery)
                .firstOrNull()

            if (topicEntity != null) {
                Result.Success(topicEntity.toQuizTopic())
            } else {
                Result.Failure(DataError.NotFound)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Result.Failure(DataError.Database)
        }
    }

    override suspend fun deleteTopicById(id: String?): Result<Unit, DataError> {
        if (id.isNullOrBlank()) {
            return Result.Failure(DataError.Validation)
        }
        return try {
            val filterQuery = Filters.eq(
                QuizTopicEntity::_id.name, id
            )
            val deleteResult = topicsCollection.deleteOne(filterQuery)
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