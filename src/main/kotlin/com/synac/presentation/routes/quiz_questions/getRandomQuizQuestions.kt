package com.synac.presentation.routes.quiz_questions

import com.synac.domain.repository.QuizQuestionsRepository
import com.synac.domain.utils.DataError
import com.synac.domain.utils.onFailure
import com.synac.domain.utils.onSuccess
import com.synac.presentation.util.respondWithError
import io.ktor.http.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.Route
import io.ktor.util.collections.*

fun Route.getRandomQuizQuestions(
    repository: QuizQuestionsRepository
) {

    get<QuizQuestionRoutesPath.Random> {path->

        repository.getRandomQuestion(path.topicCode,path.limit)
            .onSuccess {
                call.respond(
                    message = it,
                    status = HttpStatusCode.OK
                )
            }
            .onFailure {
                respondWithError(it)
            }
    }

}




