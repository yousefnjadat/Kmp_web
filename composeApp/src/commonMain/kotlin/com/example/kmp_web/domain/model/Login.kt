package com.example.kmp_web.domain.model

data class LoginRequest(
    val userId: String,
    val password: String,
    val language: Int = 0,
    val imei: String = "",
    val version: String = "",
    val deviceToken: String = ""
)

data class LoginResponse(
    val status: Boolean,
    val userId: String,
    val userName: String,
    val accessToken: String
)
