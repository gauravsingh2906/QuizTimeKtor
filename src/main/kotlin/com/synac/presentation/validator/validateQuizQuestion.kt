package com.synac.presentation.validator

import com.synac.domain.model.QuizQuestions
import io.ktor.server.plugins.requestvalidation.*

fun RequestValidationConfig.validateQuizQuestion() {
    validate<QuizQuestions>{ quizQuestions ->
        when {
            quizQuestions.question.isBlank() || quizQuestions.question.length <5 -> {
                ValidationResult.Invalid(
                    reason = "Question must not be empty and 5 characters long"
                )
            }
            quizQuestions.correctAnswer.isBlank() -> {
                ValidationResult.Invalid(
                    reason = "Correct Answer must not be empty"
                )
            }
            quizQuestions.incorrectAnswer.isEmpty() -> {
                ValidationResult.Invalid(
                    reason = "There must be at least one incorrect answer "
                )
            }
            quizQuestions.explanation.isBlank() -> {
                ValidationResult.Invalid(
                    reason = "Explanation must not be empty"
                )
            }
            quizQuestions.topicCode <=0 -> {
                ValidationResult.Invalid(
                    reason = "Topic Code must not be a positive integer"
                )
            }
            else -> {
                ValidationResult.Valid
            }

        }
    }
}