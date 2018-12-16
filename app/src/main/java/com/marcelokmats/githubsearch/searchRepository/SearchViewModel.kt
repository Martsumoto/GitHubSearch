package com.marcelokmats.githubsearch.searchRepository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.marcelokmats.githubsearch.api.ApiClient
import com.marcelokmats.githubsearch.model.RepositorySearchResult

class SearchViewModel : ViewModel() {

    val repositoriesLivedata : LiveData<RepositorySearchResult>

    val repositorySearchTextLive : MutableLiveData<String> = MutableLiveData()

    init {
        repositoriesLivedata = Transformations.switchMap(repositorySearchTextLive) {
            search -> ApiClient.INSTANCE.apiHandler.getRepositories(search) }
    }

    fun setSearchTextValue(query: String) {
        repositorySearchTextLive.value = query
    }

    fun getRepositoriesLiveData() : LiveData<RepositorySearchResult> {
        return repositoriesLivedata
    }

}