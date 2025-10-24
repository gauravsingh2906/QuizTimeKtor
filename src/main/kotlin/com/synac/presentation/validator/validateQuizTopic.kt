package com.synac.presentation.validator

import com.synac.domain.model.QuizTopics
import io.ktor.server.plugins.requestvalidation.*

fun RequestValidationConfig.validateQuizTopic() {
    validate<QuizTopics>{quizTopics ->
        when {
            quizTopics.name.isBlank() || quizTopics.name.length <3 -> {
                ValidationResult.Invalid(
                    reason = "Topic must not be empty and it needs to be 3 characters long"
                )
            }
            quizTopics.imageUrl.isBlank() -> {
                ValidationResult.Invalid(
                    reason = "Image Url must not be empty"
                )
            }

            quizTopics.code < 0 -> {
                ValidationResult.Invalid(
                    reason = "Topic Code must be a whole number"
                )
            }
            else -> {
                ValidationResult.Valid
            }

        }
    }
}