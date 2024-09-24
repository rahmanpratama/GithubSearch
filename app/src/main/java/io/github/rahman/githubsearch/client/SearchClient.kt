package io.github.rahman.githubsearch.client

import io.github.rahman.githubsearch.client.dto.GitHubUserSearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface SearchClient {
    @GET("/search/users")
    suspend fun getSearchUserList(
        @Query("q") query: String,
        @Query("page") page: Int,             // Page number for pagination
        @Query("per_page") perPage: Int
    ): Response<GitHubUserSearchResponse>
}