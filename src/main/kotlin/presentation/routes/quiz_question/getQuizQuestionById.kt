package com.synac.presentation.routes.quiz_question

import com.synac.domain.model.QuizQuestion
import com.synac.presentation.config.quizQuestions
import io.ktor.server.routing.*
import io.ktor.server.response.*

fun Route.getQuizQuestionById() {
    get(path = "/quiz/questions/{questionId}") {
        val id = call.parameters["questionId"]
        val question: QuizQuestion? = quizQuestions.find { it.id == id }
        if (question != null) {
            call.respond(question)
        }
    }
}