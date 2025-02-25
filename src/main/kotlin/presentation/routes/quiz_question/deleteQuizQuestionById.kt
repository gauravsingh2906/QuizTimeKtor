package com.synac.presentation.routes.quiz_question

import com.synac.domain.repository.QuizQuestionRepository
import com.synac.domain.util.onFailure
import com.synac.domain.util.onSuccess
import com.synac.presentation.util.respondWithError
import io.ktor.http.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.Route

fun Route.deleteQuizQuestionById(
    repository: QuizQuestionRepository
) {
    delete<QuizQuestionRoutesPath.ById> { path ->
        repository.deleteQuestionById(path.questionId)
            .onSuccess {
                call.respond(HttpStatusCode.NoContent)
            }
            .onFailure { error ->
                respondWithError(error)
            }
    }
}