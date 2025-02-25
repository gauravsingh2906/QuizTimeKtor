package com.synac.presentation.routes.quiz_topic

import com.synac.domain.repository.QuizTopicRepository
import com.synac.domain.util.onFailure
import com.synac.domain.util.onSuccess
import com.synac.presentation.util.respondWithError
import io.ktor.http.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.Route

fun Route.getQuizTopicById(
    quizTopicRepository: QuizTopicRepository
) {
    get<QuizTopicRoutesPath.ById> { path ->
        quizTopicRepository.getTopicById(path.topicId)
            .onSuccess { quizTopic ->
                call.respond(
                    message = quizTopic,
                    status = HttpStatusCode.OK
                )
            }
            .onFailure { error ->
                respondWithError(error)
            }
    }
}