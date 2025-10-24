package com.synac.domain.model

import kotlinx.serialization.Serializable
import java.sql.Timestamp

@Serializable
data class IssueReports(
    val id:String?=null,
    val questionId:String,
    val issueType:String,
    val additionalComment:String?,
    val userEmail:String?,
    val timestamp: String
)
