package com.example.kmp_web.di

import com.example.kmp_web.data.datasource.remote.ApiClient
import com.example.kmp_web.data.datasource.remote.LoginApi
import com.example.kmp_web.data.datasource.remote.LoginApiImpl
import com.example.kmp_web.data.mapper.LoginMapper
import com.example.kmp_web.data.repository.LoginRepositoryImpl
import com.example.kmp_web.domain.repository.LoginRepository
import com.example.kmp_web.domain.usecase.LoginUseCase
import com.example.kmp_web.presentation.viewmodel.HomeViewModel
import com.example.kmp_web.presentation.viewmodel.LoginViewModel
import io.ktor.client.*
import org.koin.core.context.startKoin
import org.koin.dsl.module
import com.russhwolf.settings.Settings

val appModule = module {
    single { Settings() }
    single<HttpClient> { ApiClient.create() }
    single<LoginApi> { LoginApiImpl(get(), get()) }
    single { LoginMapper() }

    // Pass the 3 arguments: Api, Mapper, Settings
    single<LoginRepository> { LoginRepositoryImpl(get(), get(), get()) }

    single { LoginUseCase(get()) }

    // ViewModels
    factory { LoginViewModel(get()) }
    factory { HomeViewModel(get()) }
}

fun initKoin() {
    startKoin {
        modules(appModule)
    }
}