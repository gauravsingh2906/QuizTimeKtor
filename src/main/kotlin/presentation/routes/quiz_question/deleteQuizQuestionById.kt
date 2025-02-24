package com.synac.presentation.routes.quiz_question

import com.synac.domain.repository.QuizQuestionRepository
import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.deleteQuizQuestionById(
    repository: QuizQuestionRepository
) {
    delete(path = "/quiz/questions/{questionId}") {
        val id = call.parameters["questionId"]
        if (id.isNullOrBlank()) {
            call.respond(
                message = "Question Id needed",
                status = HttpStatusCode.BadRequest
            )
            return@delete
        }
        val isDeleted = repository.deleteQuestionById(id)
        if (isDeleted) {
            call.respond(HttpStatusCode.NoContent)
        } else {
            call.respond(
                message = "No Question to delete",
                status = HttpStatusCode.NotFound
            )
        }
    }
}