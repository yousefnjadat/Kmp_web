package com.example.kmp_web.domain.repository

import com.example.kmp_web.common.Result
import com.example.kmp_web.domain.model.LoginRequest
import com.example.kmp_web.domain.model.LoginResponse

interface LoginRepository {
    suspend fun login(request: LoginRequest): Result<LoginResponse>
}