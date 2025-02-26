package com.synac.presentation.routes

import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.root() {
    get(path = "/") {
        call.respondText(
            text = "Welcome to the Quiz API!"
        )
    }
}