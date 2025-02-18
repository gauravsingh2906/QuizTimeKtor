package com.synac.presentation.routes.quiz_question

import com.synac.domain.model.QuizQuestion
import com.synac.presentation.config.quizQuestions
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.upsertQuizQuestion() {
    post(path = "/quiz/questions") {
        val question = call.receive<QuizQuestion>()
        quizQuestions.add(question)
        call.respondText("Question added successfully")
    }
}