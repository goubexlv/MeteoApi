package com.daccvo.routes

import com.daccvo.domain.reponse.InfoSortie
import com.daccvo.domain.reponse.StatusRepond
import com.daccvo.domain.request.ApiRequest
import com.daccvo.repository.MeteoRepository
import com.daccvo.domain.model.Endpoint
import com.daccvo.utils.RedisManager
import io.ktor.client.plugins.*
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.TimeoutCancellationException
import java.time.LocalDate

fun Route.meteoRoute(meteoRepository: MeteoRepository) {

    get(Endpoint.Meteo.path){
        val apiReponse = call.receive<ApiRequest>()
        val today = LocalDate.now()
        val cacheKey = "${apiReponse.ville},${apiReponse.pays},$today"

        if (RedisManager.testConnection()) {
            val cachedData = RedisManager.getInfoSortie(cacheKey)
            if (cachedData != null) {
                call.respond(HttpStatusCode.OK, cachedData)
                return@get
            }
        } else {
            call.respond(HttpStatusCode.InternalServerError, "Échec de la connexion à Redis ❌")
        }
        try {
            val response = meteoRepository.fetchPost(apiReponse.ville,apiReponse.pays,today)
            val infoSortie = InfoSortie(response!!.resolvedAddress,
                response!!.days[0].datetime,
                response!!.days[0].conditions,
                response!!.days[0].temp)

            RedisManager.saveInfoSortie(cacheKey, infoSortie)

            call.respond(
                message = infoSortie,
                status = HttpStatusCode.OK
            )
        } catch (e: ClientRequestException) {
            call.respond(
                message = StatusRepond(e.response.status.value, "Erreur client: ${e.message}"),
                status = HttpStatusCode.BadRequest
            )
        } catch (e: ServerResponseException) {
            call.respond(
                message = StatusRepond(e.response.status.value, "Erreur serveur: ${e.message}"),
                status = HttpStatusCode.BadRequest
            )
        } catch (e: TimeoutCancellationException) {
            call.respond(
                message = StatusRepond(408, "La requête a expiré"),
                status = HttpStatusCode.BadRequest
            )
        } catch (e: Exception) {
            call.respond(
                message = StatusRepond(500, "Erreur inattendue: ${e.localizedMessage}"),
                status = HttpStatusCode.BadRequest
            )
        }

    }

}