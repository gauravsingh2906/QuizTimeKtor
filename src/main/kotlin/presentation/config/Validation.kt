package com.synac.presentation.config

import com.synac.presentation.validator.validateQuizQuestion
import com.synac.presentation.validator.validateQuizTopic
import io.ktor.server.application.*
import io.ktor.server.plugins.requestvalidation.*

fun Application.configureValidation() {
    install(RequestValidation) {
        validateQuizQuestion()
        validateQuizTopic()
    }
}