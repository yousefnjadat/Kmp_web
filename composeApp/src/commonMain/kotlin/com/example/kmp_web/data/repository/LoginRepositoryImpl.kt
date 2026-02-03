package com.example.kmp_web.data.repository

import com.example.kmp_web.common.Result
import com.example.kmp_web.data.datasource.remote.LoginApi
import com.example.kmp_web.data.mapper.LoginMapper
import com.example.kmp_web.domain.model.LoginRequest
import com.example.kmp_web.domain.model.LoginResponse
import com.example.kmp_web.domain.repository.LoginRepository

class LoginRepositoryImpl(
    private val loginApi: LoginApi,
    private val mapper: LoginMapper
) : LoginRepository {
    override suspend fun login(request: LoginRequest): Result<LoginResponse> {
        return when (val result = loginApi.login(mapper.toDto(request))) {
            is Result.Success -> Result.Success(mapper.toDomain(result.data))
            is Result.Error -> result
            is Result.Loading -> Result.Loading
            Result.Init -> Result.Init
        }
    }
}