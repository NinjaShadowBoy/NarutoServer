val kotlinVersion: String by project
val logbackVersion: String by project
val ktorVersion: String by project
val koinVersion: String by project

plugins {
    kotlin("jvm") version "2.1.10"
    kotlin("plugin.serialization") version "2.1.10"
    id("io.ktor.plugin") version "3.0.3"
}

group = "com.example"
version = "0.0.1"

application {
    mainClass.set("io.ktor.server.netty.EngineMain")
//    mainClass.set("com.example.ApplicationKt") // For embedded server
    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core-jvm")
    implementation("io.ktor:ktor-server-netty")
    implementation("ch.qos.logback:logback-classic:$logbackVersion")
    implementation("io.ktor:ktor-server-core")
    implementation("io.ktor:ktor-server-config-yaml")
    implementation("io.ktor:ktor-server-call-logging")

    // For JSON serialization (using kotlinx.serialization)
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")

    // For content negotiation
    implementation("io.ktor:ktor-server-content-negotiation:$ktorVersion")

    // For templating with HTML DSL
    implementation("io.ktor:ktor-server-html-builder:$ktorVersion")

    // Koin for Kotlin apps
    implementation("io.insert-koin:koin-ktor:$koinVersion")
    implementation("io.insert-koin:koin-logger-slf4j:$koinVersion")

    // Default Headers
    implementation("io.ktor:ktor-server-default-headers:$ktorVersion")

    testImplementation("io.ktor:ktor-server-test-host")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlinVersion")
}
