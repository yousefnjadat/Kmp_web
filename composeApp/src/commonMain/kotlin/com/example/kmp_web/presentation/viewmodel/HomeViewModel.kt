package com.example.kmp_web.presentation.viewmodel

import com.example.kmp_web.domain.model.LoginResponse
import com.example.kmp_web.domain.repository.LoginRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: LoginRepository,
    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Main)
) {
    private val _userState = MutableStateFlow<LoginResponse?>(null)
    val userState: StateFlow<LoginResponse?> = _userState

    init {
        loadUser()
    }

    private fun loadUser() {
        coroutineScope.launch {
            _userState.value = repository.getSavedUser()
        }
    }

    fun refreshUser() {
        coroutineScope.launch {
            loadUser()
        }
    }

    fun logout(onLogoutComplete: () -> Unit) {
        coroutineScope.launch {
            onLogoutComplete()
        }
    }
}