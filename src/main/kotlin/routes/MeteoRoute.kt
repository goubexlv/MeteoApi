package com.daccvo.routes

import com.daccvo.domain.reponse.ApiResponse
import com.daccvo.domain.reponse.InfoSortie
import com.daccvo.domain.reponse.StatusRepond
import com.daccvo.domain.request.ApiRequest
import com.daccvo.repository.MeteoRepository
import com.daccvo.utils.Endpoint
import io.ktor.client.plugins.*
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.TimeoutCancellationException

fun Route.meteoRoute(meteoRepository: MeteoRepository) {

    get(Endpoint.Meteo.path){
        val apiReponse = call.receive<ApiRequest>()

        try {
            val response = meteoRepository.fetchPost(apiReponse.ville,apiReponse.pays)
            call.respond(
                message = InfoSortie(response!!.resolvedAddress,
                    response!!.days[0].datetime,
                    response!!.days[0].conditions,
                    response!!.days[0].temp),
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