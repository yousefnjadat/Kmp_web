package com.example.kmp_web.di

import com.example.kmp_web.data.datasource.remote.ApiClient
import com.example.kmp_web.data.datasource.remote.LoginApi
import com.example.kmp_web.data.datasource.remote.LoginApiImpl
import com.example.kmp_web.data.mapper.LoginMapper
import com.example.kmp_web.data.repository.LoginRepositoryImpl
import com.example.kmp_web.domain.repository.LoginRepository
import com.example.kmp_web.domain.usecase.LoginUseCase
import com.example.kmp_web.presentation.viewmodel.LoginViewModel
import io.ktor.client.*
import org.koin.core.context.startKoin
import org.koin.dsl.module

val appModule = module {
    // HTTP Client
    single<HttpClient> { ApiClient.create() }

    // API
    single<LoginApi> { LoginApiImpl(get()) }

    // Mapper
    single { LoginMapper() }

    // Repository
    single<LoginRepository> { LoginRepositoryImpl(get(), get()) }

    // Use Cases
    single { LoginUseCase(get()) }

    // ViewModels
    single { LoginViewModel(get()) }
}

fun initKoin() {
    startKoin {
        modules(appModule)
    }
}