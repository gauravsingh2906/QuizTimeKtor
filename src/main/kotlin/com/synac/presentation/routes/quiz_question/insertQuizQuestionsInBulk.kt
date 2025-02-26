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

fun Route.insertQuizQuestionsInBulk(
    repository: QuizQuestionRepository
) {
    post<QuizQuestionRoutesPath.Bulk> {
        val quizQuestion = call.receive<List<QuizQuestion>>()
        repository.insertQuestionsInBulk(quizQuestion)
            .onSuccess {
                call.respond(
                    message = "Quiz Questions added",
                    status = HttpStatusCode.Created
                )
            }
            .onFailure { error ->
                respondWithError(error)
            }
    }
}