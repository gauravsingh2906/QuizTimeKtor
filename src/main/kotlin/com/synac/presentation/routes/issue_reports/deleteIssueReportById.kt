package com.synac.presentation.routes.issue_reports

import IssueReportsRoutesPath
import com.synac.domain.model.IssueReports
import com.synac.domain.repository.IssueReportRepository
import com.synac.domain.utils.onFailure
import com.synac.domain.utils.onSuccess
import com.synac.presentation.util.respondWithError
import io.ktor.http.*
import io.ktor.server.routing.*
import io.ktor.server.resources.*
import io.ktor.server.response.*

fun Route.deleteIssueReportById(
    repository: IssueReportRepository
) {

    delete<IssueReportsRoutesPath.ById> { path ->
        repository.deleteIssueReportById(path.reportId)
            .onSuccess {
                call.respond(HttpStatusCode.NoContent)
            }.onFailure {
                respondWithError(it)
            }
    }

}