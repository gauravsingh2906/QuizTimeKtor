package com.synac.presentation.routes.issue_report

import com.synac.domain.model.IssueReport
import com.synac.domain.repository.IssueReportRepository
import com.synac.domain.util.onFailure
import com.synac.domain.util.onSuccess
import com.synac.presentation.util.respondWithError
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.routing.Route
import io.ktor.server.resources.*
import io.ktor.server.response.*

fun Route.insertIssueReport(
    repository: IssueReportRepository
) {
    post<IssueReportRoutesPath> {
        val report = call.receive<IssueReport>()
        repository.insertIssueReport(report)
            .onSuccess {
                call.respond(
                    message = "Report submitted successfully",
                    status = HttpStatusCode.Created
                )
            }
            .onFailure { error ->
                respondWithError(error)
            }
    }
}