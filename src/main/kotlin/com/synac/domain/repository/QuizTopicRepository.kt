package com.synac.domain.repository

import com.synac.domain.model.QuizTopics
import com.synac.domain.utils.DataError
import com.synac.domain.utils.Result

interface QuizTopicRepository {

    suspend fun getAllTopics():Result<List<QuizTopics>,DataError>

    suspend fun upsertTopic(topic:QuizTopics):Result<Unit,DataError>

    suspend fun getTopicById(id:String?):Result<QuizTopics,DataError>

    suspend fun deleteTopicById(id: String?):Result<Unit,DataError>

}