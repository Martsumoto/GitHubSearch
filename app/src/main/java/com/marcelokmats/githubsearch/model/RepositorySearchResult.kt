package com.marcelokmats.githubsearch.model

import com.google.gson.annotations.SerializedName

data class RepositorySearchResult(
    @SerializedName("total_count") private val mCount: Int,
    @SerializedName("items") private val mRepositories: List<Repository>
) {
    val count
        get() = mCount

    val repositories
        get() = mRepositories
}