package io.github.rahman.githubsearch.service

import io.github.rahman.githubsearch.client.SearchClient
import io.github.rahman.githubsearch.client.dto.GitHubUserSearchResponse
import retrofit2.Response

class SearchService (private val client: SearchClient) {

    suspend fun getSearchUserList(text: String, page: Int): Response<GitHubUserSearchResponse> =
        client.getSearchUserList(query = text,
            page = 0,
            perPage = 30)
}