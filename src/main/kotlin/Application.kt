package com.synac

import com.synac.presentation.config.configureLogging
import com.synac.presentation.config.configureRouting
import com.synac.presentation.config.configureSerialization
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

@Suppress("unused")
fun Application.module() {
    configureLogging()
    configureSerialization()
    configureRouting()
}
