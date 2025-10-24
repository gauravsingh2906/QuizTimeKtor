package com.synac.data.repository

import com.mongodb.client.model.Filters
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import com.synac.data.database.entity.IssueReportEntity
import com.synac.data.mapper.toIssueReport
import com.synac.data.mapper.toIssueReportEntity
import com.synac.domain.model.IssueReports
import com.synac.domain.repository.IssueReportRepository
import com.synac.domain.utils.DataError
import com.synac.domain.utils.Result
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.flow.map

class IssueReportRepositoryImpl(
    mongoDatabase: MongoDatabase
):IssueReportRepository {

    private val reportCollection = mongoDatabase
        .getCollection<IssueReportEntity>("issue_reports")

    override suspend fun getAllIssueReports(): Result<List<IssueReports>, DataError> {
       return try {

           val reports = reportCollection
               .find()
               .map { it.toIssueReport() }
               .toList()


           if(reports.isNotEmpty()) {
               Result.Success(reports)
           } else {
               Result.Failure(DataError.NotFound)
           }

        } catch (e:Exception) {
            e.printStackTrace()
            Result.Failure(DataError.Database)
        }
    }

    override suspend fun insertIssueReport(report: IssueReports): Result<Unit, DataError> {
        return try {
            reportCollection.
            insertOne(report.toIssueReportEntity())
            Result.Success(Unit)

        } catch (e:Exception) {
            e.printStackTrace()
            Result.Failure(DataError.Database)
        }
    }

    override suspend fun deleteIssueReportById(id: String?): Result<Unit, DataError> {
        if(id.isNullOrBlank()) {
            return Result.Failure(DataError.Validation)
        }
        return try {
             val filterQuery= Filters.eq(
                 IssueReportEntity::_id.name,id
             )
           val deleteResult = reportCollection.deleteOne(filterQuery)
            if(deleteResult.deletedCount>0) {
                Result.Success(Unit)
            } else {
                Result.Failure(DataError.NotFound)
            }


        } catch (e:Exception) {
            e.printStackTrace()
            Result.Failure(DataError.Database)
        }
    }
}