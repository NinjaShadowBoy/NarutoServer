package com.example

import com.example.models.ApiResponse
import com.example.repository.HeroRepo
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import junit.framework.TestCase.assertEquals
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement
import org.koin.java.KoinJavaComponent.inject
import kotlin.test.Test

class ApplicationTest {
    @Test
    fun `access root endpoint, check correct information`() = testApplication {
        application {
            module()
        }

        val response = client.get("/")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals("Welcome to Boruto API!", response.bodyAsText())
    }


    private val heroRepository: HeroRepo by inject(HeroRepo::class.java)

    @ExperimentalSerializationApi
    @Test
    fun `access AllHeroes endpoint, check correct info`() = testApplication {
        application {
            module()
        }

        val response = client.get("/boruto/heroes")
        assertEquals(HttpStatusCode.OK, response.status)
        val format = Json { explicitNulls = true }
        val expectedResponse = format.encodeToString(
            ApiResponse(
                success = true,
                message = "Ok for page number: 1",
                prevPage = null,
                nextPage = 2,
                heroes = heroRepository.page1
            )
        )
        println(expectedResponse)


        val actualResponse = response.readRawBytes().toString(Charsets.UTF_8)
        assertEquals(expectedResponse, actualResponse)
    }


}