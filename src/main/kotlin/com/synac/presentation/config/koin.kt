package com.synac.presentation.config

import io.ktor.server.application.*
import org.koin.ktor.plugin.koin
import com.synac.di.koinModule
import io.ktor.http.ContentType
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun Application.configureKoin() {

    install(Koin) {
        slf4jLogger()
        modules(com.synac.di.koinModule)
    }

}