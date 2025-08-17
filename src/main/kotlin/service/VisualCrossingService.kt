package com.daccvo.service

import com.daccvo.domain.reponse.ApiResponse
import com.daccvo.utils.Constants.VISUALCROSSING_API
import com.daccvo.utils.client
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDate

object VisualCrossingService {
    suspend fun getMeteo(ville: String, pays: String, today : LocalDate) : ApiResponse {
        return withContext(Dispatchers.IO) {
            val url = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/$ville,$pays/$today?key=$VISUALCROSSING_API"
            client.get(url).body<ApiResponse>()
        }
    }
}