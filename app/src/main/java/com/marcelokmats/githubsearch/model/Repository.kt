package com.marcelokmats.githubsearch.model

import com.google.gson.annotations.SerializedName

data class Repository(
    @SerializedName("id") private val mId: Int,
    @SerializedName("name") private val mName: String
) {

    val name
        get() = mName

}