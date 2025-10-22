package com.synac.domain.repository

import com.synac.domain.model.IssueReports
import com.synac.domain.utils.DataError
import com.synac.domain.utils.Result

interface IssueReportRepository {

    suspend fun getAllIssueReports():Result<List<IssueReports>,DataError>

    suspend fun insertIssueReport(report: IssueReports):Result<Unit,DataError>

    suspend fun deleteIssueReportById(id:String?):Result<Unit,DataError>


}