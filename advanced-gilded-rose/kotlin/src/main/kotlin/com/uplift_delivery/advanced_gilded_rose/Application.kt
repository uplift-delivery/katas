package com.uplift_delivery.advanced_gilded_rose


import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.serialization.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args);

fun Application.module() {
    install(ContentNegotiation) {
        json()
    }

    routing {
        get("/") {
            val controller = ItemsController()
            call.respond(controller.getAll())
        }

        post("/") {
            val controller = ItemsController()
            controller.update()
            call.respond(HttpStatusCode.NoContent)
        }
    }
}