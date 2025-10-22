package com.synac.data.mapper

import com.synac.data.database.entity.IssueReportEntity
import com.synac.domain.model.IssueReports

fun IssueReportEntity.toIssueReport() = IssueReports(
    id=_id,
    questionId=questionId,
    issueType = issueType,
    additionalComment = additionalComment,
    userEmail=userEmail,
    timestamp=timestamp
)

fun IssueReports.toIssueReportEntity() = IssueReportEntity(
    questionId=questionId,
    issueType = issueType,
    additionalComment = additionalComment,
    userEmail=userEmail,
    timestamp=timestamp
)