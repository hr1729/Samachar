package com.androiddevs.mvvmnewsapp.api

import com.androiddevs.mvvmnewsapp.data.newsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface services {
    companion object {
        const val BASE_URL = "https://newsapi.org/v2/"
        const val API_KEY="4e6614543e674a66b20e226f3724e18e"

    }
    @GET("v2/top-headlines")
    suspend fun getBreakingNews(
        @Query("country")
        countryCode: String = "us",
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = API_KEY
    ): Response<newsResponse>

    @GET("v2/everything")
    suspend fun searchForNews(
        @Query("q")
        searchQuery: String,
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = API_KEY
    ): Response<newsResponse>
}