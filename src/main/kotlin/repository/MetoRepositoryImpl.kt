package com.daccvo.repository

import com.daccvo.domain.reponse.ApiResponse
import com.daccvo.service.VisualCrossingService
import com.daccvo.utils.Constants.VISUALCROSSING_API
import com.daccvo.utils.client
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDate


class MeteoRepositoryImpl : MeteoRepository {
    override suspend fun fetchPost(ville: String, pays: String, today : LocalDate): ApiResponse? {
        return VisualCrossingService.getMeteo(ville,pays,today)
    }

}