package com.ee.kmp.data

import com.ee.kmp.domine.MainRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.http.path
import kotlinx.serialization.json.Json

class MainRepositoryImp(
    private val httpClient: HttpClient,
    private val json: Json
): MainRepository {

    override suspend fun getData(): List<Breed>? {
        //api_key=live_gyX4Aur2uY2uZLQpYbwY5VN2hlrXQJU0uxmwbfdI9UFoU9dJcLnhuQ8qvHgvovTl&limit=10
        val response = httpClient.get("https://api.thecatapi.com/v1/breeds")

        println(response.status)

        return when(response.status) {
            HttpStatusCode.OK -> {
                json.decodeFromString<List<Breed>>(response.body<String>())
            }
            HttpStatusCode.BadRequest -> {
                null
            }
            HttpStatusCode.InternalServerError -> {
                null
            }
            HttpStatusCode.Unauthorized -> {
                null
            }
            else -> {
                null
            }
        }
    }

}