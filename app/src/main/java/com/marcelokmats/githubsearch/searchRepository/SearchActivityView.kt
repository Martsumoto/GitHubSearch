package com.marcelokmats.githubsearch.searchRepository

import com.marcelokmats.githubsearch.model.Repository

interface SearchActivityView {
    fun onRepositoryClick(repository: Repository)
}
