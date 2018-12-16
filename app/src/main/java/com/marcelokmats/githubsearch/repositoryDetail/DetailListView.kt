package com.marcelokmats.githubsearch.repositoryDetail

import com.marcelokmats.githubsearch.model.Issue
import com.marcelokmats.githubsearch.model.Pull

interface DetailListView {
    fun onIssueClick(issue: Issue)
    fun onPullClick(pull: Pull)
}
