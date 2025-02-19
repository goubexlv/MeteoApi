package com.daccvo.plugins

import com.daccvo.repository.MeteoRepository
import com.daccvo.routes.meteoRoute
import com.daccvo.routes.rootRoute
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
    routing {
        val meteoRepository : MeteoRepository by application.inject()
        rootRoute()
        meteoRoute(meteoRepository)
    }
}
