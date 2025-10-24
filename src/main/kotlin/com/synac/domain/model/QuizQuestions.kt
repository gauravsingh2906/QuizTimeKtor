package com.synac.domain.model

import kotlinx.serialization.Serializable
import java.util.Collections.emptyList

@Serializable
data class QuizQuestions(
    val id:String?=null,
    val question:String,
    val correctAnswer:String,
    val incorrectAnswer: List<String> = emptyList(),
    val explanation:String,
    val topicCode:Int
)
