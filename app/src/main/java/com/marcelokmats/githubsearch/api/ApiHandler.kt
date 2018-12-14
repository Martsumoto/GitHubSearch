package com.marcelokmats.githubsearch.api

import com.marcelokmats.githubsearch.model.RepositorySearchResult

class ApiHandler(val apiInterface: ApiInterface) {
    fun getRepositories(query : String) : RetrofitLiveData<RepositorySearchResult> = RetrofitLiveData(apiInterface.getRepositories(query))

}