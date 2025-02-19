package com.daccvo.repository

import com.daccvo.domain.reponse.ApiResponse
import com.daccvo.utils.Constants.KEY_API
import com.daccvo.utils.client
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.TimeoutCancellationException
import java.time.LocalDate


class MeteoRepositoryImpl : MeteoRepository {
    override suspend fun fetchPost(ville: String, pays: String): ApiResponse? {
        val today = LocalDate.now()
        val url = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/$ville,$pays/$today?key=$KEY_API"
        return client.get(url).body<ApiResponse>()
    }

}