package com.synac.presentation.validator

import com.synac.domain.model.IssueReports
import io.ktor.server.plugins.requestvalidation.*

fun RequestValidationConfig.validateIssueReport() {
    validate<IssueReports>{ issueReport ->

        val emailRegex = Regex(pattern = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}\$")


        when {
            issueReport.questionId.isBlank()  -> {
                ValidationResult.Invalid(
                    reason = "Question Id must not be empty"
                )
            }
            issueReport.issueType.isBlank() -> {
                ValidationResult.Invalid(
                    reason = "Issue Type must not be empty"
                )
            }
            issueReport.timestamp.isBlank() -> {
                ValidationResult.Invalid(
                    reason = "Timestamp must not be empty"
                )
            }
            issueReport.additionalComment !=null && issueReport.additionalComment.length<5 -> {
                ValidationResult.Invalid(
                    reason = "Additional Comment must be at least 5 characters long."
                )
            }
            issueReport.userEmail !=null && !issueReport.userEmail.matches(emailRegex) -> {
                ValidationResult.Invalid(
                    reason = "Invalid Email Format"
                )
            }
            else -> {
                ValidationResult.Valid
            }

        }
    }
}