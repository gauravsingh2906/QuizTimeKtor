package com.synac.presentation.routes.quiz_topic

import com.synac.domain.repository.QuizTopicRepository
import com.synac.domain.util.onFailure
import com.synac.domain.util.onSuccess
import com.synac.presentation.util.respondWithError
import io.ktor.http.*
import io.ktor.server.routing.*
import io.ktor.server.resources.*
import io.ktor.server.response.*

fun Route.getAllQuizTopics(
    repository: QuizTopicRepository
) {
    get<QuizTopicRoutesPath> {
        repository.getAllTopics()
            .onSuccess { topics ->
                call.respond(
                    message = topics,
                    status = HttpStatusCode.OK
                )
            }
            .onFailure { error ->
                respondWithError(error)
            }
    }
}