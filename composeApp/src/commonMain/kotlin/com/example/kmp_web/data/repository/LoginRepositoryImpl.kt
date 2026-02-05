package com.example.kmp_web.data.repository

import com.example.kmp_web.common.Result
import com.example.kmp_web.data.datasource.remote.LoginApi
import com.example.kmp_web.data.dto.LoginResponseDto
import com.example.kmp_web.data.mapper.LoginMapper
import com.example.kmp_web.domain.model.LoginRequest
import com.example.kmp_web.domain.model.LoginResponse
import com.example.kmp_web.domain.repository.LoginRepository
import com.russhwolf.settings.Settings
import kotlinx.serialization.json.Json

class LoginRepositoryImpl(
    private val loginApi: LoginApi,
    private val mapper: LoginMapper,
    private val settings: Settings // Ensure this is injected
) : LoginRepository {

    override suspend fun login(request: LoginRequest): Result<LoginResponse> {
        return when (val result = loginApi.login(mapper.toDto(request))) {
            is Result.Success -> Result.Success(mapper.toDomain(result.data))
            else -> result as Result<LoginResponse>
        }
    }

    override suspend fun getSavedUser(): LoginResponse? {
        val jsonString = settings.getString("login_response_json", "")
        return if (jsonString.isNotEmpty()) {
            try {
                val dto = Json.decodeFromString<LoginResponseDto>(jsonString)
                LoginMapper().toDomain(dto)
            } catch (e: Exception) {
                null
            }
        } else null
    }

    override fun clearSession() {
        settings.remove("login_response_json")
    }

}