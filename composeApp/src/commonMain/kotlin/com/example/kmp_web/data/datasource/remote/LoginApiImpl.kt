package com.example.kmp_web.data.datasource.remote

import com.example.kmp_web.common.Result
import com.example.kmp_web.data.dto.LoginRequestDto
import com.example.kmp_web.data.dto.LoginResponseDto
import com.russhwolf.settings.Settings
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.HttpResponse
import io.ktor.http.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class LoginApiImpl(private val client: HttpClient, private val settings: Settings) : LoginApi {

    override suspend fun login(request: LoginRequestDto): Result<LoginResponseDto> {
        return try {
            val response = client.post("Login") {
                contentType(ContentType.Application.Json)
                setBody(request)
            }

            val loginResponse: LoginResponseDto = response.body()
            println("API LoginResponseDto: $loginResponse")


            if (loginResponse.status == true) {
                val json = Json { ignoreUnknownKeys = true }
                val jsonString = json.encodeToString(loginResponse)
                settings.putString("login_response_json", jsonString)
                Result.Success(loginResponse)
            } else {
                Result.Error(
                    Exception(
                        loginResponse.resultMessage?.ifEmpty { "Login failed" }
                    )
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Result.Error(e)
        }
    }
}