package com.example.plugins

import com.example.routes.getAllHeroes
import com.example.routes.root
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable

fun Application.configureRouting() {
    routing {
        staticResources("/images", "/images")

        root()
        getAllHeroes()
    }
}

@Serializable
data class Person(val name: String, val age: Int)
