package com.example.kmp_web

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.kmp_web.presentation.theme.AppTheme
import com.example.kmp_web.presentation.ui.HomeScreen
import com.example.kmp_web.presentation.ui.LoginScreen
import com.example.kmp_web.presentation.viewmodel.HomeViewModel
import com.example.kmp_web.presentation.viewmodel.LoginViewModel
import org.koin.compose.koinInject
import com.example.kmp_web.common.Result

@Composable
fun App() {
    AppTheme {
        val loginViewModel: LoginViewModel = koinInject()
        val homeViewModel: HomeViewModel = koinInject()
        val loginState by loginViewModel.loginState.collectAsState()

        Surface(modifier = Modifier.fillMaxSize()) {
            if (loginState is Result.Success) {
                homeViewModel.refreshUser()
                HomeScreen(
                    viewModel = homeViewModel,
                    onLogout = {
                        loginViewModel.logout()
                    }
                )
            } else {
                LoginScreen(viewModel = loginViewModel)
            }
        }
    }
}