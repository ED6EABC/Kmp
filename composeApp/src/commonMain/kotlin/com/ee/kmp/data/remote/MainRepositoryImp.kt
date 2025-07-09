package com.ee.kmp.data.remote

import com.ee.kmp.data.model.Breed
import com.ee.kmp.domine.MainRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode
import kotlinx.serialization.json.Json

class MainRepositoryImp(
    private val httpClient: HttpClient
): MainRepository {

    override suspend fun getData(): List<Breed>? {
        //api_key=live_gyX4Aur2uY2uZLQpYbwY5VN2hlrXQJU0uxmwbfdI9UFoU9dJcLnhuQ8qvHgvovTl&limit=10

        val response = httpClient.get(urlString = "https://api.thecatapi.com/v1/breeds")

        return when(response.status) {
            HttpStatusCode.Companion.OK -> {
                response.body<List<Breed>>()
                //json.decodeFromString<List<Breed>>(response.body<String>())
            }
            HttpStatusCode.Companion.BadRequest -> {
                null
            }
            HttpStatusCode.Companion.InternalServerError -> {
                null
            }
            HttpStatusCode.Companion.Unauthorized -> {
                null
            }
            else -> {
                null
            }
        }
    }

}