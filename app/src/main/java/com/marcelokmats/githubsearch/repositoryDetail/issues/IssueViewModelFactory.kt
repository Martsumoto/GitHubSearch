package com.marcelokmats.githubsearch.repositoryDetail.issues

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.marcelokmats.githubsearch.model.Issue
import com.marcelokmats.githubsearch.model.Repository

class IssueViewModelFactory(val repository: Repository, val issue: Issue) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return IssueDetailViewModel(repository, issue) as T
    }


}