package com.example.kmp_web.presentation.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kmp_web.common.Result
import com.example.kmp_web.presentation.viewmodel.LoginViewModel

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    modifier: Modifier = Modifier
) {
    var userId by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val loginState by viewModel.loginState

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Display login status
        when (loginState) {
            is Result.Loading -> {
                CircularProgressIndicator()
                Text("Logging in...")
            }
            is Result.Success -> {
                val data = (loginState as Result.Success).data
                Text(
                    text = "Login Successful!\nStatus: ${data.status}",
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = data.userId,
                    color = MaterialTheme.colorScheme.secondary
                )
                Text(
                    text = data.userName ?: "",
                    color = MaterialTheme.colorScheme.secondary
                )
                Text(
                    text = data.accessToken ?: "",
                    color = MaterialTheme.colorScheme.secondary
                )
            }
            is Result.Error -> {
                val error = (loginState as Result.Error).exception
                Text(
                    text = "Error: ${error.message}",
                    color = MaterialTheme.colorScheme.error
                )
            }
            else -> {
                // Not logged in state
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Login Form
        OutlinedTextField(
            value = userId,
            onValueChange = { userId = it },
            label = { Text("User ID") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Login Button
        Button(
            onClick = { viewModel.login(userId, password) },
            modifier = Modifier.fillMaxWidth(),
            enabled = userId.isNotEmpty() && password.isNotEmpty()
        ) {
            Text("Login")
        }

        // Clear Button
        Button(
            onClick = { viewModel.clearState() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer
            )
        ) {
            Text("Clear State")
        }
    }
}