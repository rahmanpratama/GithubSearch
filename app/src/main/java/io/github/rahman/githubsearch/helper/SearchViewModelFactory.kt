package io.github.rahman.githubsearch.helper

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.github.rahman.githubsearch.service.SearchService
import io.github.rahman.githubsearch.ui.SearchViewModel

class SearchViewModelFactory(
    private val searchService: SearchService
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            return SearchViewModel(searchService) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}