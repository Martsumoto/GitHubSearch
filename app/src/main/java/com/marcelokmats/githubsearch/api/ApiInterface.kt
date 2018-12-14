package com.marcelokmats.githubsearch.api

import com.marcelokmats.githubsearch.model.RepositorySearchResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("search/repositories")
    fun getRepositories(@Query("q") repository: String = "") : Call<RepositorySearchResult>
}