package com.marcelokmats.githubsearch.api

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient private constructor() {

    companion object Singleton {
        val INSTANCE: ApiClient by lazy { ApiClient() }

        const val BASE_URL = "https://api.github.com"
    }

    val apiHandler: ApiHandler

    init {
        val gson = GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()


        apiHandler = ApiHandler(retrofit.create(ApiInterface::class.java))
    }
}