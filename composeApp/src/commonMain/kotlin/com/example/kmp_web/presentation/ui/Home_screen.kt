package com.example.kmp_web.presentation.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kmp_web.presentation.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onLogout: () -> Unit
) {
    val user by viewModel.userState.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Welcome Home!", style = MaterialTheme.typography.headlineMedium)

        user?.let {
            Spacer(modifier = Modifier.height(8.dp))
            Text("User ID: ${it.userId}")
            Text("Name: ${it.userName}")
            Text("Token: ${it.accessToken.take(10)}...", color = MaterialTheme.colorScheme.secondary)
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = { viewModel.logout(onLogout) }) {
            Text("Logout")
        }
    }
}