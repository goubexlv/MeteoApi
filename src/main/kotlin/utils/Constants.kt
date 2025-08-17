package com.daccvo.utils

import io.github.cdimascio.dotenv.dotenv
import kotlin.String

object Constants {
    val dotenv = dotenv()
//    val VISUALCROSSING_API : String = dotenv["VISUALCROSSING_API"].toString()
//    val HOST_REDIS : String = dotenv["REDIS_HOST"].toString()
//    val PORT_REDIS : Int = dotenv["REDIS_PORT"].toInt()
//    val USERNAME_REDIS : String = dotenv["USERNAME_REDIS"].toString()
//    val PASSWORD_REDIS : String = dotenv["PASSWORD_REDIS"].toString()

    val VISUALCROSSING_API : String = System.getenv("VISUALCROSSING_API").toString()
    val HOST_REDIS : String = System.getenv("REDIS_HOST").toString()
    val PORT_REDIS : Int = 6379

    const val TIME = 1800L


}