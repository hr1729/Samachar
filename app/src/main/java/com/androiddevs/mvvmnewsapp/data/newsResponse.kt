package com.androiddevs.mvvmnewsapp.data
import com.google.gson.annotations.SerializedName
data class newsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)
