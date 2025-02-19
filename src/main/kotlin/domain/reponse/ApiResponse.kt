package com.daccvo.domain.reponse


import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse(
    val resolvedAddress: String,
    val days: List<Info>,
)

@Serializable
data class Info(
    val datetime: String,
    val conditions: String,
    val description : String,
    val icon : String,
    val temp: Double,
)

@Serializable
data class StatusRepond(
    val status: Int,
    val message: String
)

@Serializable
data class ApiError(
    val status: Int,
    val message: String
)

@Serializable
data class InfoSortie(
    val adresse : String,
    val jour: String,
    val conditions: String,
    val temperature: Double
)
