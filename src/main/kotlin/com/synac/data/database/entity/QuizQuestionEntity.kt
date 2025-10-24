package com.synac.data.database.entity

import org.bson.codecs.pojo.annotations.BsonCreator
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId
import java.util.*

data class QuizQuestionEntity(
    @BsonId
    val _id:String=ObjectId().toString(),
    val question:String,
    val correctAnswer:String,
    val incorrectAnswer: List<String> = Collections.emptyList(),
    val explanation:String,
    val topicCode:Int
)
