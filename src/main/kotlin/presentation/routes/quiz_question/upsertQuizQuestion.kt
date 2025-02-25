package com.synac.presentation.routes.quiz_question

import com.synac.domain.model.QuizQuestion
import com.synac.domain.repository.QuizQuestionRepository
import com.synac.domain.util.onFailure
import com.synac.domain.util.onSuccess
import com.synac.presentation.util.respondWithError
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.Route

fun Route.upsertQuizQuestion(
    repository: QuizQuestionRepository
) {
    post<QuizQuestionRoutesPath> {
        val question = call.receive<QuizQuestion>()
        repository.upsertQuestion(question)
            .onSuccess {
                call.respond(
                    message = "Question added successfully",
                    status = HttpStatusCode.Created
                )
            }
            .onFailure { error ->
                respondWithError(error)
            }
    }
}