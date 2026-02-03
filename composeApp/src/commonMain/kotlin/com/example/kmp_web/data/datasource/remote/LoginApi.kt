package com.example.kmp_web.data.datasource.remote

import com.example.kmp_web.common.Result
import com.example.kmp_web.data.dto.LoginRequestDto
import com.example.kmp_web.data.dto.LoginResponseDto

interface LoginApi {
    suspend fun login(request: LoginRequestDto): Result<LoginResponseDto>
}