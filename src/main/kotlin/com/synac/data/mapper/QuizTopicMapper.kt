package com.synac.data.mapper

import com.synac.data.database.entity.QuizTopicEntity
import com.synac.domain.model.QuizTopics

fun QuizTopicEntity.toQuizTopic() = QuizTopics(
    id=_id,
    name = name,
    imageUrl=imageUrl,
    code=code
)


fun QuizTopics.toQuizTopicEntity() = QuizTopicEntity(
    name = name,
    imageUrl=imageUrl,
    code=code
)