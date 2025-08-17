package com.daccvo.utils

import io.github.cdimascio.dotenv.dotenv
import kotlin.String

object Constants {
    val dotenv = dotenv()
    val VISUALCROSSING_API : String = dotenv["VISUALCROSSING_API"].toString()
    val HOST_REDIS : String = dotenv["REDIS_HOST"].toString()
    val PORT_REDIS : Int = dotenv["REDIS_PORT"].toInt()

//    val VISUALCROSSING_API : String = System.getenv("VISUALCROSSING_API").toString()
//    val HOST_REDIS : String = System.getenv("REDIS_HOST").toString()
//    val PORT_REDIS : Int = System.getenv("REDIS_PORT").toInt()
    const val TIME = 1800L


}