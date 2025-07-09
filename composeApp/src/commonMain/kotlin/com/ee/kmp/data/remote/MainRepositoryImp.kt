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

    override suspend fun getData(request: APIs.Breeds.Request): APIs.Breeds.Response {

        val response = httpClient.get(urlString = APIs.Breeds.url) {
            parameter("page", request.page)
            parameter("limit", request.limit)
        }

        return when(response.status) {
            HttpStatusCode.Companion.OK -> {
                APIs.Breeds.Response(
                    response.body<List<Breed>>(),
                    response.headers["pagination-count"]?.toInt() ?: 0
                )
            }
            HttpStatusCode.Companion.BadRequest -> {
                APIs.Breeds.Response(
                    listOf(),
                    0
                )
            }
            HttpStatusCode.Companion.InternalServerError -> {
                APIs.Breeds.Response(
                    listOf(),
                    0
                )
            }
            HttpStatusCode.Companion.Unauthorized -> {
                APIs.Breeds.Response(
                    listOf(),
                    0
                )
            }
            else -> {
                APIs.Breeds.Response(
                    listOf(),
                    0
                )
            }
        }
    }

}