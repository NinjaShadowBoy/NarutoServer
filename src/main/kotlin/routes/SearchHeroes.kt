package com.example.routes

import com.example.repository.HeroRepo
import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.searchHeroes() {
    // Singleton instance of HeroRepo from koin
    val heroRepository: HeroRepo by application.inject()

    get("/boruto/heroes/search") {
        val name = call.request.queryParameters["name"].toString()

        val apiResponse = heroRepository.searchHeroes(name = name)
        call.respond(message = apiResponse, status = HttpStatusCode.OK)
    }
}