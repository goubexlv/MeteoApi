package com.daccvo.repository

import com.daccvo.domain.reponse.ApiResponse

interface MeteoRepository {

    suspend fun fetchPost(ville:String,pays:String) : ApiResponse?
}