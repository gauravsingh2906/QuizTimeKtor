package com.synac.presentation.config

import com.synac.domain.model.QuizQuestion
import com.synac.presentation.routes.quiz_question.getAllQuizQuestions
import com.synac.presentation.routes.quiz_question.upsertQuizQuestion
import com.synac.presentation.routes.root
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {

    routing {

        root()

        getAllQuizQuestions()
        upsertQuizQuestion()

    }
}

val quizQuestions = mutableListOf<QuizQuestion>()