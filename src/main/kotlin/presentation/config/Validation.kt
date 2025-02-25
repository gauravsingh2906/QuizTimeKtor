package com.synac.presentation.config

import com.synac.presentation.validator.validateQuizQuestion
import io.ktor.server.application.*
import io.ktor.server.plugins.requestvalidation.*

fun Application.configureValidation() {
    install(RequestValidation) {
        validateQuizQuestion()
    }
}