package com.marcelokmats.githubsearch.searchRepository

import android.arch.lifecycle.ViewModel
import com.marcelokmats.githubsearch.api.ApiClient
import com.marcelokmats.githubsearch.api.RetrofitLiveData
import com.marcelokmats.githubsearch.model.RepositorySearchResult

class SearchViewModel : ViewModel() {

    lateinit var repositoriesLivedata : RetrofitLiveData<RepositorySearchResult>

    fun searchRepositories(query : String) : RetrofitLiveData<RepositorySearchResult> {
        repositoriesLivedata = ApiClient.INSTANCE.apiHandler.getRepositories(query)

        return repositoriesLivedata
    }


}