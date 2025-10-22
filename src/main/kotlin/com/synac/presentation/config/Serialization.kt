package com.synac.presentation.config

import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.websocket.WebSocketDeflateExtension.Companion.install

fun Application.configureSerialization() {

    install(ContentNegotiation) {
        json()
    }
}