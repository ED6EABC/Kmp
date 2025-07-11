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

    override suspend fun getData(request: APIs.Breeds.Request): HttpResponse<APIs.Breeds.Response> {

        return try {
            val response = httpClient.get(urlString = APIs.Breeds.url) {
                parameter("page", request.page)
                parameter("limit", request.limit)
            }

            when(response.status) {
                HttpStatusCode.Companion.OK -> {
                    HttpResponse.Success(
                        APIs.Breeds.Response(
                            response.body<List<Breed>>(),
                            response.headers["pagination-count"]?.toInt() ?: 0
                        )
                    )
                }
                HttpStatusCode.Companion.BadRequest, HttpStatusCode.Companion.Unauthorized  -> {
                    HttpResponse.Error(
                        response.status.value,
                        response.body()
                    )
                }
                else -> {
                    HttpResponse.Exception
                }
            }

        } catch (_: Exception) {
            HttpResponse.Exception
        }
    }

}