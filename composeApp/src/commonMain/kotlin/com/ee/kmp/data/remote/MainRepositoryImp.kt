package com.ee.kmp.data.remote

import com.ee.kmp.data.model.Breed
import com.ee.kmp.domine.MainRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.HttpStatusCode
import kotlinx.serialization.json.Json

class MainRepositoryImp(
    private val httpClient: HttpClient
): MainRepository {

    override suspend fun getData(page: Int, limit: Int): List<Breed> {

        val response = httpClient.get(urlString = APIs.Breeds.url) {
            parameter("page", page)
            parameter("limit", limit)
        }

        return when(response.status) {
            HttpStatusCode.Companion.OK -> {
                response.body<List<Breed>>()
                //json.decodeFromString<List<Breed>>(response.body<String>())
            }
            HttpStatusCode.Companion.BadRequest -> {
                listOf()
            }
            HttpStatusCode.Companion.InternalServerError -> {
                listOf()
            }
            HttpStatusCode.Companion.Unauthorized -> {
                listOf()
            }
            else -> {
                listOf()
            }
        }
    }

}