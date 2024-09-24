package io.github.rahman.githubsearch.container

import io.github.rahman.githubsearch.base.GitHubConnectionModule
import io.github.rahman.githubsearch.client.SearchClient
import retrofit2.Retrofit

internal class ClientContainer {
    private val connectionModule: GitHubConnectionModule by lazy {
        GitHubConnectionModule(Container.commonContainer.cache)
    }

    private val retrofit: Retrofit by lazy {
        connectionModule.provideRetrofit()
    }

    val searchClient: SearchClient by lazy {
        retrofit.create(SearchClient::class.java)
    }
}