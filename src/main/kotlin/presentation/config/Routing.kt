package com.synac.presentation.config

import com.synac.presentation.routes.quiz_question.getAllQuizQuestions
import com.synac.presentation.routes.root
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {

    routing {

        root()

        getAllQuizQuestions()

    }
}