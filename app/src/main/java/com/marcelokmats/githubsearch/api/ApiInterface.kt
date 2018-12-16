package com.marcelokmats.githubsearch.api

import com.marcelokmats.githubsearch.model.Issue
import com.marcelokmats.githubsearch.model.IssueComment
import com.marcelokmats.githubsearch.model.Pull
import com.marcelokmats.githubsearch.model.RepositorySearchResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    @GET("search/repositories")
    fun getRepositories(@Query("q") repository: String = "") : Call<RepositorySearchResult>

    @GET("repos/{user}/{repo}/issues")
    fun getIssues(@Path("user") user: String, @Path("repo") repo: String) : Call<List<Issue>>

    @GET("repos/{user}/{repo}/pulls")
    fun getPulls(@Path("user") user: String, @Path("repo") repo: String) : Call<List<Pull>>

    @GET("repos/{user}/{repo}/issues/{issueNumber}/comments")
    fun getIssueComments(@Path("user") user: String, @Path("repo") repo: String, @Path("issueNumber") issueNumber: Long) : Call<List<IssueComment>>
}