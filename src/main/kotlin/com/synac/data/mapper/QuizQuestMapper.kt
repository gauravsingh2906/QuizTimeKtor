package com.synac.data.mapper

import com.synac.data.database.entity.QuizQuestionEntity
import com.synac.domain.model.QuizQuestions

fun QuizQuestionEntity.toQuizQuestion() = QuizQuestions(
    id=_id,
    question= question,
    correctAnswer=correctAnswer,
    incorrectAnswer=incorrectAnswer,
    explanation=explanation,
    topicCode=topicCode
)

fun QuizQuestions.toQuizQuestionEntity() = QuizQuestionEntity(
    question= question,
    correctAnswer=correctAnswer,
    incorrectAnswer=incorrectAnswer,
    explanation=explanation,
    topicCode=topicCode
)