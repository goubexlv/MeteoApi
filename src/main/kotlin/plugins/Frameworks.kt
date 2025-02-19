package com.daccvo.plugins


import com.daccvo.di.KoinModule
import io.ktor.server.application.*
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun Application.configurationFrameworks(){
    install(Koin) {
        slf4jLogger()
        modules(KoinModule)
    }

}