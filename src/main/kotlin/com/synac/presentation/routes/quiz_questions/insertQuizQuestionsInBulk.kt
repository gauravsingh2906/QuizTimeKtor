package com.synac.presentation.routes.quiz_questions

import com.synac.domain.model.QuizQuestions
import com.synac.domain.repository.QuizQuestionsRepository
import com.synac.domain.utils.onFailure
import com.synac.domain.utils.onSuccess
import com.synac.presentation.util.respondWithError
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.Route

fun Route.insertQuizQuestionsInBulk(
    repository: QuizQuestionsRepository
) {
    post<QuizQuestionRoutesPath.Bulk> {
        val quizQuestion = call.receive<List<QuizQuestions>>()
        repository.insertQuestionsInBulk(quizQuestion)
            .onSuccess {
                call.respond(
                    message = "Quiz Questions Added",
                    status = HttpStatusCode.Created                )
            }
            .onFailure {
                respondWithError(it)
            }
    }
}