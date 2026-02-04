package com.example.kmp_web.data.datasource.remote

import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class ApiClient {
    companion object {
        private const val BASE_URL = "https://brother-security.com/webservice/SmartSteps/Germany/api/v1/"

        fun create(): HttpClient = HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    ignoreUnknownKeys = true
                    isLenient = true
                })
            }

            install(HttpTimeout) {
                requestTimeoutMillis = 30000
                connectTimeoutMillis = 30000
                socketTimeoutMillis = 30000
            }

            // CORRECT LOGGING CONFIGURATION:
            install(Logging) {
                level = LogLevel.ALL
                // Remove the logger = Logger.DEFAULT line
                // Or use: logger = Logger.DEFAULT // If you have the right import
            }

            defaultRequest {
                url(BASE_URL)
                header(io.ktor.http.HttpHeaders.Accept, "application/json")
                header(io.ktor.http.HttpHeaders.AcceptCharset, "utf-8")
            }
        }
    }
}