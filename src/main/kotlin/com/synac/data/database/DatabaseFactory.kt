package com.synac.data.database

import com.mongodb.kotlin.client.coroutine.MongoClient
import com.mongodb.kotlin.client.coroutine.MongoDatabase


object DatabaseFactory {

    fun create() : MongoDatabase {
        val connectionString = System.getenv("MONGO_URI") ?: throw IllegalArgumentException("MONGO_URI is not set")
        val databaseName = "QuizTime_db"
        val mongoClient = MongoClient.create(connectionString)
       return mongoClient.getDatabase(databaseName)
    }


}