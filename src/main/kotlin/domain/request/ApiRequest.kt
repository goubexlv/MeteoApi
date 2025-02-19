package com.daccvo.domain.request

import kotlinx.serialization.Serializable

@Serializable
data class ApiRequest(
    val ville : String,
    val pays : String
)
