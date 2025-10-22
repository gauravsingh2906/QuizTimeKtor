package com.synac.di

import com.synac.data.database.DatabaseFactory
import com.synac.data.repository.QuizQuestionRepositoryImpl
import com.synac.data.repository.QuizTopicRepositoryImpl
import com.synac.data.repository.IssueReportRepositoryImpl
import com.synac.domain.repository.IssueReportRepository
import com.synac.domain.repository.QuizQuestionsRepository
import com.synac.domain.repository.QuizTopicRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val koinModule = module {

    single { DatabaseFactory.create() }

    singleOf(::QuizQuestionRepositoryImpl).bind<QuizQuestionsRepository>()
    singleOf(::IssueReportRepositoryImpl).bind<IssueReportRepository>()
    singleOf(::QuizTopicRepositoryImpl).bind<QuizTopicRepository>()



}