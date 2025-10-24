package com.synac.presentation.routes.issue_reports

import IssueReportsRoutesPath
import com.synac.domain.model.IssueReports
import com.synac.domain.repository.IssueReportRepository
import com.synac.domain.utils.onFailure
import com.synac.domain.utils.onSuccess
import com.synac.presentation.util.respondWithError
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.routing.Route
import io.ktor.server.resources.*
import io.ktor.server.response.*

fun Route.insertIssueReport(
    repository: IssueReportRepository
) {

    post<IssueReportsRoutesPath> {

        val report = call.receive<IssueReports>()
        repository.insertIssueReport(report)
            .onSuccess {
                call.respond(
                    message = "Report Submitted Successfully",
                    status=HttpStatusCode.Created
                )
            }
            .onFailure {
                respondWithError(it)
            }

    }

}