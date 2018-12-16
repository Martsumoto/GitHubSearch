package com.marcelokmats.githubsearch.repositoryDetail.issues

import android.arch.lifecycle.ViewModel
import com.marcelokmats.githubsearch.api.ApiClient
import com.marcelokmats.githubsearch.api.RetrofitLiveData
import com.marcelokmats.githubsearch.model.Issue
import com.marcelokmats.githubsearch.model.IssueComment
import com.marcelokmats.githubsearch.model.Repository

class IssueDetailViewModel(val repository: Repository, val issue: Issue)  : ViewModel() {

    lateinit var issueCommentsLiveData : RetrofitLiveData<List<IssueComment>>

    fun fetchIssueComments(user : String, repo : String, issueNumber : Long) : RetrofitLiveData<List<IssueComment>> {
        issueCommentsLiveData = ApiClient.INSTANCE.apiHandler.getIssueComments(user, repo, issueNumber)

        return issueCommentsLiveData
    }
}