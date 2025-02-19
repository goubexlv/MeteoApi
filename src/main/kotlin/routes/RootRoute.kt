package com.daccvo.routes

import com.daccvo.utils.Endpoint
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.rootRoute(){
    get(Endpoint.Root.path){
        call.respond(message = "Bienvenue sur mon API!")
    }
}