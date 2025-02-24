package com.synac.presentation.config

import com.synac.data.database.DatabaseFactory
import com.synac.data.repository.QuizQuestionRepositoryImpl
import com.synac.domain.repository.QuizQuestionRepository
import com.synac.presentation.routes.quiz_question.deleteQuizQuestionById
import com.synac.presentation.routes.quiz_question.getAllQuizQuestions
import com.synac.presentation.routes.quiz_question.getQuizQuestionById
import com.synac.presentation.routes.quiz_question.upsertQuizQuestion
import com.synac.presentation.routes.root
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {

    val mongoDatabase = DatabaseFactory.create()
    val quizQuestionRepository: QuizQuestionRepository = QuizQuestionRepositoryImpl(mongoDatabase)

    routing {

        root()

        getAllQuizQuestions(quizQuestionRepository)
        upsertQuizQuestion(quizQuestionRepository)
        getQuizQuestionById(quizQuestionRepository)
        deleteQuizQuestionById(quizQuestionRepository)

    }
}