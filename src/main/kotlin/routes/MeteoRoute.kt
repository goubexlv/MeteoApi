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
        val ville = call.request.queryParameters["ville"]
        val pays = call.request.queryParameters["pays"]
        val today = LocalDate.now()
        val cacheKey = "${ville},${pays},$today"

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
            if (pays != null && ville != null) {
                val response = meteoRepository.fetchPost(ville,pays,today)
                val infoSortie = InfoSortie(response!!.resolvedAddress,
                    response!!.days[0].datetime,
                    response!!.days[0].conditions,
                    response!!.days[0].temp)
                RedisManager.saveInfoSortie(cacheKey, infoSortie)
                call.respond(
                    message = infoSortie,
                    status = HttpStatusCode.OK
                )
            } else {
                // Si l'un des paramètres est manquant
                call.respond(HttpStatusCode.BadRequest, "Paramètres manquants")
            }
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