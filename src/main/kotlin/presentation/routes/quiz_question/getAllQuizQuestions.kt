package com.synac.presentation.routes.quiz_question

import com.synac.domain.model.QuizQuestion
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.getAllQuizQuestions() {
    get(path = "/quiz/questions") {
        val question = QuizQuestion(
            question = "Which company developed Kotlin?",
            correctAnswer = "JetBrains",
            incorrectAnswers = listOf("Microsoft", "Google", "Facebook"),
            explanation = "Some Explanation",
            topicCode = 1
        )
        call.respondText(
            question.toString()
        )
    }
}