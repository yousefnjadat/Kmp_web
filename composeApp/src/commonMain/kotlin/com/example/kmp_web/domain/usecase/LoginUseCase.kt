package com.example.kmp_web.domain.usecase

import com.example.kmp_web.common.Result
import com.example.kmp_web.domain.model.LoginRequest
import com.example.kmp_web.domain.model.LoginResponse
import com.example.kmp_web.domain.repository.LoginRepository

class LoginUseCase(private val repository: LoginRepository) {
    suspend operator fun invoke(request: LoginRequest) = repository.login(request)
    suspend fun getSavedUser() = repository.getSavedUser()
    fun logout() = repository.clearSession()
}