package com.daccvo.repository

import com.daccvo.domain.reponse.ApiResponse
import com.daccvo.utils.Constants.KEY_API
import com.daccvo.utils.client
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.withContext
import java.time.LocalDate


class MeteoRepositoryImpl : MeteoRepository {
    override suspend fun fetchPost(ville: String, pays: String, today : LocalDate): ApiResponse? {
        return withContext(Dispatchers.IO) {
            val url = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/$ville,$pays/$today?key=$KEY_API"
            client.get(url).body<ApiResponse>()
        }
    }

}