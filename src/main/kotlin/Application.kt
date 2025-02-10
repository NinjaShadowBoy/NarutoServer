package com.example

import com.example.plugins.*
import com.example.repository.HeroRepo
import com.example.repository.HeroRepository
import io.ktor.server.application.*
import org.koin.ktor.plugin.koin

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    setupKoin()

    configureSerialization()
    configureDefaultHeader()
    configureMonitoring()
    configureRouting()
}
