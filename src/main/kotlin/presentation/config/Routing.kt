package com.synac.presentation.config

import com.synac.data.database.DatabaseFactory
import com.synac.data.repository.QuizQuestionRepositoryImpl
import com.synac.data.repository.QuizTopicRepositoryImpl
import com.synac.domain.repository.QuizQuestionRepository
import com.synac.domain.repository.QuizTopicRepository
import com.synac.presentation.routes.quiz_question.deleteQuizQuestionById
import com.synac.presentation.routes.quiz_question.getAllQuizQuestions
import com.synac.presentation.routes.quiz_question.getQuizQuestionById
import com.synac.presentation.routes.quiz_question.upsertQuizQuestion
import com.synac.presentation.routes.quiz_topic.deleteQuizTopicById
import com.synac.presentation.routes.quiz_topic.getAllQuizTopics
import com.synac.presentation.routes.quiz_topic.getQuizTopicById
import com.synac.presentation.routes.quiz_topic.upsertQuizTopic
import com.synac.presentation.routes.root
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.resources.*
import io.ktor.server.routing.*

fun Application.configureRouting() {

    install(Resources)

    val mongoDatabase = DatabaseFactory.create()
    val quizQuestionRepository: QuizQuestionRepository = QuizQuestionRepositoryImpl(mongoDatabase)
    val quizTopicRepository: QuizTopicRepository = QuizTopicRepositoryImpl(mongoDatabase)

    routing {

        root()

        //Quiz Question
        getAllQuizQuestions(quizQuestionRepository)
        upsertQuizQuestion(quizQuestionRepository)
        getQuizQuestionById(quizQuestionRepository)
        deleteQuizQuestionById(quizQuestionRepository)

        //Quiz Topic
        getAllQuizTopics(quizTopicRepository)
        upsertQuizTopic(quizTopicRepository)
        getQuizTopicById(quizTopicRepository)
        deleteQuizTopicById(quizTopicRepository)

        staticResources(
            remotePath = "/images",
            basePackage = "images"
        )

    }
}