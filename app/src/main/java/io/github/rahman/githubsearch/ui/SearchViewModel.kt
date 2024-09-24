package io.github.rahman.githubsearch.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import io.github.rahman.githubsearch.base.BaseViewModel
import io.github.rahman.githubsearch.client.dto.GitHubUser
import io.github.rahman.githubsearch.helper.EventData
import io.github.rahman.githubsearch.service.SearchService
import kotlinx.coroutines.launch

class SearchViewModel(private val service: SearchService): BaseViewModel() {

    private val _liveSuccessGetUsers: MutableLiveData<EventData<List<GitHubUser>>> by lazy { MutableLiveData() }
    val liveSuccessGetUsers: LiveData<EventData<List<GitHubUser>>> get() = _liveSuccessGetUsers


    fun search(keyword: String) {
        viewModelScope.launch(exceptionHandler) {
            val response =
                service.getSearchUserList(text = keyword, 0)
                    .body()!!
            _liveSuccessGetUsers.value = EventData(content = response.items)
        }
    }

}