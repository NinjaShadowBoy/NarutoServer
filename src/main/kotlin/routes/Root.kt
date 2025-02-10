package com.example.routes

import io.ktor.server.response.*
import io.ktor.server.routing.*

// Here we create route endpoints

fun Route.root() {
    get("/") {
        call.respondText("Welcome to Boruto API!")
    }
}