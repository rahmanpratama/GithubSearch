package io.github.rahman.githubsearch.container

import io.github.rahman.githubsearch.service.SearchService

class ServiceContainer {
    val scannerService: SearchService by lazy {
        SearchService(Container.clientContainer.searchClient)
    }
}
