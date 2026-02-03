package com.example.kmp_web

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform