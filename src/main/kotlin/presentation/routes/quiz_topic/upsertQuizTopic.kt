package com.synac.presentation.routes.quiz_topic

import com.synac.domain.model.QuizTopic
import com.synac.domain.repository.QuizTopicRepository
import com.synac.domain.util.onFailure
import com.synac.domain.util.onSuccess
import com.synac.presentation.util.respondWithError
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.Route

fun Route.upsertQuizTopic(
    quizTopicRepository: QuizTopicRepository
) {
    post<QuizTopicRoutesPath> {
        val quizTopic = call.receive<QuizTopic>()
        quizTopicRepository.upsertTopic(quizTopic)
            .onSuccess {
                call.respond(
                    message = "Quiz Topic added",
                    status = HttpStatusCode.Created
                )
            }
            .onFailure { error ->
                respondWithError(error)
            }
    }
}