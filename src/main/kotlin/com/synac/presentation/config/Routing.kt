package com.synac.presentation.config

import com.synac.data.database.DatabaseFactory
import com.synac.domain.repository.IssueReportRepository
import com.synac.domain.repository.QuizQuestionsRepository
import com.synac.domain.repository.QuizTopicRepository
import com.synac.presentation.routes.issue_reports.deleteIssueReportById
import com.synac.presentation.routes.issue_reports.getAllIssueReport
import com.synac.presentation.routes.issue_reports.insertIssueReport
import com.synac.presentation.routes.quiz_questions.*
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

fun Application.configRouting() {

    install(Resources)

    val mondoDatabase = DatabaseFactory.create()
    val quizQuestionsRepository:QuizQuestionsRepository by inject()

    val quizTopicRepository:QuizTopicRepository by inject()

    val issueReportRepository:IssueReportRepository by inject()

    routing {
        root()

        //Quiz Questions
        getAllQuizQuestions(quizQuestionsRepository)
        upsertQuizQuestions(quizQuestionsRepository)
        insertQuizQuestionsInBulk(quizQuestionsRepository)
        deleteQuizQuestionById(quizQuestionsRepository)
        getRandomQuizQuestions(quizQuestionsRepository)
        getQuizQuestionById(quizQuestionsRepository)

        //Quiz Topics
        getAllQuizTopics(quizTopicRepository)
        upsertQuizTopic(quizTopicRepository)
        getQuizTopicById(quizTopicRepository)
        deleteQuizTopicById(quizTopicRepository)

        //Issue Reports
        getAllIssueReport(issueReportRepository)
        insertIssueReport(issueReportRepository)
        deleteIssueReportById(issueReportRepository)

        staticResources(
            remotePath = "/images",
            basePackage ="images"
        )


    }
}

