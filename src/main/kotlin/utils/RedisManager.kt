package com.daccvo.utils

import com.daccvo.domain.reponse.InfoSortie
import com.daccvo.utils.Constants.HOST_REDIS
import com.daccvo.utils.Constants.PASSWORD_REDIS
import com.daccvo.utils.Constants.PORT_REDIS
import com.daccvo.utils.Constants.TIME
import com.daccvo.utils.Constants.USERNAME_REDIS
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import redis.clients.jedis.Jedis

object RedisManager {
    private val jedis: Jedis

    init {

        jedis = Jedis(HOST_REDIS, PORT_REDIS, true)
        jedis.auth(USERNAME_REDIS, PASSWORD_REDIS)
    }


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
        jedis.expire(key, TIME)
    }

    fun getInfoSortie(key: String): InfoSortie? {
        val json = jedis.get(key) ?: return null
        return Json.decodeFromString(json)
    }

}