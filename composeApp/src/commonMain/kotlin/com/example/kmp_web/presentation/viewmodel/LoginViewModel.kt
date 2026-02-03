package com.example.kmp_web.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.example.kmp_web.common.Result
import com.example.kmp_web.domain.model.LoginRequest
import com.example.kmp_web.domain.model.LoginResponse
import com.example.kmp_web.domain.usecase.LoginUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase,
    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Main)
) {
    private val _loginState = mutableStateOf<Result<LoginResponse>?>(null)
    val loginState: State<Result<LoginResponse>?> = _loginState

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

            _loginState.value = loginUseCase(request)
        }
    }

    fun clearState() {
        _loginState.value = null
    }
}