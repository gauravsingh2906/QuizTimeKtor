package com.synac.presentation.routes.quiz_question

import com.synac.domain.model.QuizQuestion
import com.synac.domain.repository.QuizQuestionRepository
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.upsertQuizQuestion(
    repository: QuizQuestionRepository
) {
    post(path = "/quiz/questions") {
        val question = call.receive<QuizQuestion>()
        repository.upsertQuestion(question)
        call.respond(
            message = "Question added successfully",
            status = HttpStatusCode.Created
        )
    }
}