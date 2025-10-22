package com.synac.presentation.routes.issue_reports

import IssueReportsRoutesPath
import com.synac.domain.repository.IssueReportRepository
import com.synac.domain.utils.onFailure
import com.synac.domain.utils.onSuccess
import com.synac.presentation.util.respondWithError
import io.ktor.http.*
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.resources.*

fun Route.getAllIssueReport(
    repository: IssueReportRepository
) {

    get<IssueReportsRoutesPath> {
        repository.getAllIssueReports()
            .onSuccess {reports->
                call.respond(
                    message = reports,
                    status=HttpStatusCode.OK
                )
            }
            .onFailure {
                respondWithError(it)
            }
    }

}