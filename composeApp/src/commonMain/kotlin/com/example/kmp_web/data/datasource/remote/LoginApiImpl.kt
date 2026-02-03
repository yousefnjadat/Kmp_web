package com.example.kmp_web.data.datasource.remote

import com.example.kmp_web.common.Result
import com.example.kmp_web.data.dto.LoginRequestDto
import com.example.kmp_web.data.dto.LoginResponseDto
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class LoginApiImpl(private val client: HttpClient) : LoginApi {

    override suspend fun login(request: LoginRequestDto): Result<LoginResponseDto> {
        return try {
            val response: LoginResponseDto = client.post("Login") {
                contentType(ContentType.Application.Json)
                setBody(request)
            }.body()

            // 🔹 Print raw API response
            println("API LoginResponseDto: $response")

            if (response.status == true) {
                Result.Success(response)
            } else {
                Result.Error(
                    Exception(
                        response.resultMessage?.ifEmpty { "Login failed" }
                    )
                )
            }

        } catch (e: Exception) {
            e.printStackTrace()
            Result.Error(e)
        }
    }
}

