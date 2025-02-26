package com.synac.data.mapper

import com.synac.data.database.entity.QuizQuestionEntity
import com.synac.domain.model.QuizQuestion

fun QuizQuestionEntity.toQuizQuestion() = QuizQuestion (
    id = _id,
    question = question,
    correctAnswer = correctAnswer,
    incorrectAnswers = incorrectAnswers,
    explanation = explanation,
    topicCode = topicCode
)

fun QuizQuestion.toQuizQuestionEntity() = QuizQuestionEntity (
    question = question,
    correctAnswer = correctAnswer,
    incorrectAnswers = incorrectAnswers,
    explanation = explanation,
    topicCode = topicCode
)