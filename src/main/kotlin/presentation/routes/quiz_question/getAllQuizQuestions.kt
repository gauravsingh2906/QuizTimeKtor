package com.synac.presentation.routes.quiz_question

import com.synac.presentation.config.quizQuestions
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.getAllQuizQuestions() {
    get(path = "/quiz/questions") {
        val topicCode = call.queryParameters["topicCode"]?.toIntOrNull()
        val limit = call.queryParameters["limit"]?.toIntOrNull()
        val filteredQuestions = quizQuestions
            .filter { it.topicCode == topicCode }
            .take(limit ?: 1)

        call.respond(filteredQuestions)
    }
}