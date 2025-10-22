package com.synac.presentation.routes.quiz_questions

import com.synac.domain.repository.QuizQuestionsRepository
import com.synac.domain.utils.onFailure
import com.synac.domain.utils.onSuccess
import com.synac.presentation.util.respondWithError
import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.resources.*
import io.ktor.server.routing.Route

fun Route.getQuizQuestionById(
    repository: QuizQuestionsRepository
) {

    get<QuizQuestionRoutesPath.ById> {path->

        repository.getQuestionById(path.questionId)
            .onSuccess {question->
                call.respond(message = question, status = HttpStatusCode.OK)
            }.onFailure {
                respondWithError(it)
            }

    }
}