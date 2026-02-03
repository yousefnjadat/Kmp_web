package com.example.kmp_web


import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import com.example.kmp_web.presentation.theme.AppTheme
import com.example.kmp_web.presentation.ui.LoginScreen
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject

@Composable
@Preview
fun App() {
    MaterialTheme {
        val viewModel: com.example.kmp_web.presentation.viewmodel.LoginViewModel = koinInject()

        AppTheme {
            LoginScreen(viewModel = viewModel)
        }
    }
}