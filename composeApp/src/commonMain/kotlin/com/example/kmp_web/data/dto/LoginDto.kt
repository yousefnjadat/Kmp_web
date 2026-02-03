package com.example.kmp_web.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginRequestDto(
    @SerialName("USER_ID")
    val userId: String,

    @SerialName("USER_PWD")
    val password: String,

    @SerialName("USER_LANG")
    val language: Int,

    @SerialName("USER_IMEI")
    val imei: String,

    @SerialName("VERSION")
    val version: String,

    @SerialName("DEVICE_TOKEN")
    val deviceToken: String
)


@Serializable
data class LoginResponseDto(

    @SerialName("SYS_USERS")
    val user: SysUserDto? = null,

    @SerialName("AccessToken")
    val accessToken: String? = null,

    @SerialName("Status")
    val status: Boolean? = null,

    @SerialName("ResultMessage")
    val resultMessage: String? = null
)

@Serializable
data class SysUserDto(

    @SerialName("SYS_ID")
    val id: Int? = null,

    @SerialName("SYS_USERID")
    val userId: String? = null,

    @SerialName("SYS_USER_NAME")
    val userName: String? = null,

    @SerialName("SYS_TYPE")
    val type: Int? = null,

    @SerialName("SYS_TYPE_NAME")
    val typeName: String? = null,

    @SerialName("SYS_STATUS")
    val status: Int? = null
)
