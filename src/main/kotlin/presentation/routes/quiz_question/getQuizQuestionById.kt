package com.synac.presentation.routes.quiz_question

import com.synac.domain.repository.QuizQuestionRepository
import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.getQuizQuestionById(
    repository: QuizQuestionRepository
) {
    get(path = "/quiz/questions/{questionId}") {
        val id = call.parameters["questionId"]
        if (id.isNullOrBlank()) {
            call.respond(
                message = "Question Id needed",
                status = HttpStatusCode.BadRequest
            )
            return@get
        }
        val question = repository.getQuestionById(id)
        if (question != null) {
            call.respond(
                message = question,
                status = HttpStatusCode.OK
            )
        } else {
            call.respond(
                message = "No Quiz Question",
                status = HttpStatusCode.NotFound
            )
        }
    }
}