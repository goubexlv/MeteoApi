package com.daccvo.utils

import io.github.cdimascio.dotenv.dotenv
import kotlin.String

object Constants {
    val dotenv = dotenv()
    val VISUALCROSSING_API : String = dotenv["VISUALCROSSING_API"].toString()
    val HOST_REDIS : String = dotenv["HOST_REDIS"].toString()
    val PORT_REDIS : Int = dotenv["PORT_REDIS"].toInt()
    const val TIME = 1800L


}