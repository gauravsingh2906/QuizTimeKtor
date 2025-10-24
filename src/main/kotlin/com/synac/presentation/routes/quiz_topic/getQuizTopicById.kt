package com.synac.presentation.routes.quiz_topic

import com.synac.domain.repository.QuizTopicRepository
import com.synac.domain.utils.onFailure
import com.synac.domain.utils.onSuccess
import com.synac.presentation.util.respondWithError
import io.ktor.http.*
import io.ktor.server.routing.Route
import io.ktor.server.resources.*
import io.ktor.server.response.*

fun Route.getQuizTopicById(
    topicRepository: QuizTopicRepository
) {
    get<QuizTopicRoutesPath.ById> {path->
        topicRepository.getTopicById(path.topicId)
            .onSuccess {quizTopic->
                call.respond(
                    message = quizTopic,
                    status = HttpStatusCode.OK
                )
            }.onFailure {
                respondWithError(it)
            }
    }
}