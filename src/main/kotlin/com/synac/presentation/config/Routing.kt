package com.synac.presentation.config

import com.synac.domain.repository.IssueReportRepository
import com.synac.domain.repository.QuizQuestionRepository
import com.synac.domain.repository.QuizTopicRepository
import com.synac.presentation.routes.issue_report.deleteIssueReportById
import com.synac.presentation.routes.issue_report.getAllIssueReports
import com.synac.presentation.routes.issue_report.insertIssueReport
import com.synac.presentation.routes.quiz_question.*
import com.synac.presentation.routes.quiz_topic.deleteQuizTopicById
import com.synac.presentation.routes.quiz_topic.getAllQuizTopics
import com.synac.presentation.routes.quiz_topic.getQuizTopicById
import com.synac.presentation.routes.quiz_topic.upsertQuizTopic
import com.synac.presentation.routes.root
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.resources.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {

    install(Resources)

    val quizQuestionRepository: QuizQuestionRepository by inject()
    val quizTopicRepository: QuizTopicRepository by inject()
    val issueReportRepository: IssueReportRepository by inject()

    routing {

        root()

        //Quiz Question
        getAllQuizQuestions(quizQuestionRepository)
        upsertQuizQuestion(quizQuestionRepository)
        insertQuizQuestionsInBulk(quizQuestionRepository)
        getQuizQuestionById(quizQuestionRepository)
        getRandomQuizQuestions(quizQuestionRepository)
        deleteQuizQuestionById(quizQuestionRepository)

        //Quiz Topic
        getAllQuizTopics(quizTopicRepository)
        upsertQuizTopic(quizTopicRepository)
        getQuizTopicById(quizTopicRepository)
        deleteQuizTopicById(quizTopicRepository)

        //Issue Report
        getAllIssueReports(issueReportRepository)
        insertIssueReport(issueReportRepository)
        deleteIssueReportById(issueReportRepository)

        staticResources(
            remotePath = "/images",
            basePackage = "images"
        )

    }
}