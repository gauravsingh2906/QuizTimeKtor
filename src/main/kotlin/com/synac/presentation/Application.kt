package com.synac.presentation

import com.synac.presentation.config.*
import io.ktor.server.application.*
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.koin.dsl.module
import java.sql.DriverManager.println

fun main(args: Array<String>) {

    // ✅ Use dynamic port assigned by hosting platforms (Render, Railway, etc.)
    val port = System.getenv("PORT")?.toInt() ?: 8080
    val host = "0.0.0.0" // ✅ Makes your app accessible from anywhere

    println("🚀 Server running at http://$host:$port/")

    embeddedServer(Netty, port = port, host = host) {
        module()
    }.start(wait = true)


  //  io.ktor.server.netty.EngineMain.main(args)
}

@Suppress("unused")
fun Application.module() {
    configureKoin()
    configureValidation()
    configureLogging()
    configRouting()
    configureSerialization()
    configureStatusPages()
}
