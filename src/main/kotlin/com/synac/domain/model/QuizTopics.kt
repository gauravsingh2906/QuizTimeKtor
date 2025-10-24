package com.synac.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class QuizTopics(
    val id:String?=null,
    val name:String,
    val imageUrl:String,
    val code:Int
)
