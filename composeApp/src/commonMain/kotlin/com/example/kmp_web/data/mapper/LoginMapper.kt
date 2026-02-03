package com.example.kmp_web.data.mapper

import com.example.kmp_web.data.dto.LoginRequestDto
import com.example.kmp_web.data.dto.LoginResponseDto
import com.example.kmp_web.domain.model.LoginRequest
import com.example.kmp_web.domain.model.LoginResponse

class LoginMapper {
    fun toDto(domain: LoginRequest): LoginRequestDto = LoginRequestDto(
        userId = domain.userId,
        password = domain.password,
        language = domain.language,
        imei = domain.imei,
        version = domain.version,
        deviceToken = domain.deviceToken
    )

    fun toDomain(dto: LoginResponseDto): LoginResponse = LoginResponse(
        userId = dto.user?.userId ?: "",
        userName = dto.user?.userName ?: "",
        accessToken = dto.accessToken ?: "",
        status = dto.status ?: false
    )
}