package com.daccvo.utils

import com.daccvo.domain.reponse.InfoSortie
import com.daccvo.utils.Constants.IP
import com.daccvo.utils.Constants.PORT
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import redis.clients.jedis.Jedis

object RedisManager {
    private val jedis: Jedis = Jedis(IP, PORT)

    fun testConnection(): Boolean {
        return try {
            jedis.ping() == "PONG"
        } catch (e: Exception) {
            false
        }
    }

    fun saveInfoSortie(key: String, info: InfoSortie) {
        val json = Json.encodeToString(info)
        jedis.set(key,json)
        jedis.expire(key, 1800)
    }

    fun getInfoSortie(key: String): InfoSortie? {
        val json = jedis.get(key) ?: return null
        return Json.decodeFromString(json)
    }

}