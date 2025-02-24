package com.synac.presentation.routes.quiz_question

import com.synac.domain.repository.QuizQuestionRepository
import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.getAllQuizQuestions(
    repository: QuizQuestionRepository
) {
    get(path = "/quiz/questions") {
        val topicCode = call.queryParameters["topicCode"]?.toIntOrNull()
        val limit = call.queryParameters["limit"]?.toIntOrNull()
        val questions = repository.getAllQuestions(topicCode, limit)
        if (questions.isNotEmpty()) {
            call.respond(
                message = questions,
                status = HttpStatusCode.OK
            )
        } else {
            call.respond(
                message = "No Quiz Questions",
                status = HttpStatusCode.NotFound
            )
        }
    }
}