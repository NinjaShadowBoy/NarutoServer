package com.example.plugins

import com.example.di.koinModule
import io.ktor.server.application.*
import org.koin.ktor.plugin.koin
import org.koin.logger.slf4jLogger

fun Application.setupKoin() {
    // Equivalent to install(Koin) {
    //    slf4jLogger()
    //    modules(koinModule)
    // }
    koin {
        slf4jLogger()
        modules(koinModule)
    }
}