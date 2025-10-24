package com.synac.presentation.routes.quiz_topic

import com.synac.domain.repository.QuizTopicRepository
import com.synac.domain.utils.onFailure
import com.synac.domain.utils.onSuccess
import com.synac.presentation.util.respondWithError
import io.ktor.http.*
import io.ktor.server.resources.*
import io.ktor.server.routing.*
import io.ktor.server.response.*

fun Route.deleteQuizTopicById(
    topicRepository: QuizTopicRepository
) {
    delete<QuizTopicRoutesPath.ById> {path->
        topicRepository.deleteTopicById(path.topicId)
            .onSuccess {
               call.respond(HttpStatusCode.NoContent)
            }.onFailure {
                respondWithError(it)
            }
    }
}