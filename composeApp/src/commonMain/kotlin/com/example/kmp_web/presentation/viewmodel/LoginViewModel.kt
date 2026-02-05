package com.example.kmp_web.presentation.viewmodel

import com.example.kmp_web.common.Result
import com.example.kmp_web.data.dto.LoginResponseDto
import com.example.kmp_web.data.mapper.LoginMapper
import com.example.kmp_web.domain.model.LoginRequest
import com.example.kmp_web.domain.model.LoginResponse
import com.example.kmp_web.domain.usecase.LoginUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginViewModel(
    private val loginUseCase: LoginUseCase,
    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Main)
) {
    private val _loginState = MutableStateFlow<Result<LoginResponse>>(Result.Init)
    val loginState = _loginState.asStateFlow()

    init {
        checkUserSession()
    }

    private fun checkUserSession() {
        coroutineScope.launch {
            val savedUser = loginUseCase.getSavedUser()
            if (savedUser != null) {
                _loginState.value = Result.Success(savedUser)
            }
        }
    }

    fun logout() {
        loginUseCase.logout()
        _loginState.value = Result.Init
    }

    private var loginJob: Job? = null

    fun login(userId: String, password: String) {
        loginJob?.cancel()
        _loginState.value = Result.Loading

        loginJob = coroutineScope.launch {
            val request = LoginRequest(
                userId = userId,
                password = password,
                language = 0,
                imei = "test_device",
                version = "1.0.0",
                deviceToken = "test_token"
            )
            val result = loginUseCase(request)

            _loginState.value = result
        }
    }


    fun clearState() {
        _loginState.value = Result.Init
    }
}
