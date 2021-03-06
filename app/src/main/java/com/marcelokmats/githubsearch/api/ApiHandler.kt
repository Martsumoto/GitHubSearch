package com.marcelokmats.githubsearch.api

import com.marcelokmats.githubsearch.model.Issue
import com.marcelokmats.githubsearch.model.IssueComment
import com.marcelokmats.githubsearch.model.Pull
import com.marcelokmats.githubsearch.model.RepositorySearchResult

class ApiHandler(val apiInterface: ApiInterface) {
    fun getRepositories(query : String) : RetrofitLiveData<RepositorySearchResult> = RetrofitLiveData(apiInterface.getRepositories(query))

    fun getIssues(user: String, repo: String) : RetrofitLiveData<List<Issue>> = RetrofitLiveData(apiInterface.getIssues(user, repo))

    fun getPulls(user: String, repo: String) : RetrofitLiveData<List<Pull>> = RetrofitLiveData(apiInterface.getPulls(user, repo))

    fun getIssueComments(user: String, repo: String, issueNumber: Long) : RetrofitLiveData<List<IssueComment>> = RetrofitLiveData(apiInterface.getIssueComments(user, repo, issueNumber))
}