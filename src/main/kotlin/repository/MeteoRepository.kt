package com.daccvo.repository

import com.daccvo.domain.reponse.ApiResponse
import java.time.LocalDate

interface MeteoRepository {

    suspend fun fetchPost(ville:String,pays:String,today : LocalDate) : ApiResponse?
}