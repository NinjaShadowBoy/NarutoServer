package com.example.routes

import com.example.models.ApiResponse
import com.example.repository.HeroRepo
import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.getAllHeroes() {
    // Singleton instance of HeroRepo from koin
    val heroRepository: HeroRepo by inject()

    get("/boruto/heroes") {
        try {
            val page = call.request.queryParameters["page"]?.toInt() ?: 1
            require(page in 1..5)

            val apiResponse = heroRepository.getAllHeroes(page)

            call.respond(message = apiResponse, status = HttpStatusCode.OK)
        } catch (e: NumberFormatException) {
            call.respond(
                status = HttpStatusCode.BadRequest,
                message = ApiResponse(success = false, message = "Only numbers allowed for page numbers")
            )
        } catch (e: IllegalArgumentException) {
            call.respond(
                status = HttpStatusCode.NotFound,
                message = ApiResponse(success = false, message = "Page number not found. ")
            )
        }
    }
}