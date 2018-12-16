package com.marcelokmats.githubsearch.repositoryDetail

import android.arch.lifecycle.ViewModel
import com.marcelokmats.githubsearch.api.ApiClient
import com.marcelokmats.githubsearch.api.RetrofitLiveData
import com.marcelokmats.githubsearch.model.Issue
import com.marcelokmats.githubsearch.model.Pull
import com.marcelokmats.githubsearch.model.Repository

class DetailViewModel(val repository: Repository) : ViewModel() {

    lateinit var issuesLiveData : RetrofitLiveData<List<Issue>>
    lateinit var pullsLiveData : RetrofitLiveData<List<Pull>>

    fun fetchIssues(user : String, repo : String) : RetrofitLiveData<List<Issue>> {
        issuesLiveData = ApiClient.INSTANCE.apiHandler.getIssues(user, repo)

        return issuesLiveData
    }

    fun fetchPulls(user : String, repo : String) : RetrofitLiveData<List<Pull>> {
        pullsLiveData = ApiClient.INSTANCE.apiHandler.getPulls(user, repo)

        return pullsLiveData
    }


}