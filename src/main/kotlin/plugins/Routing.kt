package com.example.plugins

import com.example.routes.getAllHeroes
import com.example.routes.root
import com.example.routes.searchHeroes
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable
import javax.security.sasl.AuthenticationException

fun Application.configureRouting() {
    routing {
        staticResources("/images", "/images")

        root()
        getAllHeroes()
        searchHeroes()

        get("/test") {
            throw AuthenticationException()
        }
    }
}
