package com.synac.presentation.util

import com.synac.domain.util.DataError
import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

suspend fun RoutingContext.respondWithError(
    error: DataError
) {
    when(error) {
        DataError.Database -> {
            call.respond(
                message = "Database error occurred",
                status = HttpStatusCode.InternalServerError
            )
        }
        DataError.NotFound -> {
            call.respond(
                message = "Resource not Found",
                status = HttpStatusCode.NotFound
            )
        }
        DataError.Validation -> {
            call.respond(
                message = "Invalid data provided",
                status = HttpStatusCode.BadRequest
            )
        }
        DataError.Unknown -> {
            call.respond(
                message = "An unknown error occurred",
                status = HttpStatusCode.InternalServerError
            )
        }
    }
}