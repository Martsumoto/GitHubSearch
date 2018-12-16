package com.marcelokmats.githubsearch.searchRepository

import com.marcelokmats.githubsearch.model.Repository

interface SearchView {
    fun onRepositoryClick(repository: Repository)
}
