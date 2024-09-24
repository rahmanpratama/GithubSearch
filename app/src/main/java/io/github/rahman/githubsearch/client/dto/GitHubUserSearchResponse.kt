package io.github.rahman.githubsearch.client.dto

data class GitHubUser(
    val login: String,
    val id: Int,
    val avatar_url: String,
    val url: String
)

data class GitHubUserSearchResponse(
    val total_count: Int,
    val incomplete_results: Boolean,
    val items: List<GitHubUser>
)