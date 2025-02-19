package com.daccvo.utils

sealed class Endpoint (val path : String) {
    object Root: Endpoint(path = "/")
    object Meteo : Endpoint(path = "/meteo")
}