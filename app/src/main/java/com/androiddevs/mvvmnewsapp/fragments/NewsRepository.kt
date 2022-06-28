package com.androiddevs.mvvmnewsapp.fragments

import com.androiddevs.mvvmnewsapp.api.RetrofitInstance
import com.androiddevs.mvvmnewsapp.data.Article
import com.androiddevs.mvvmnewsapp.database.articleDatabase

class NewsRepository(val db:articleDatabase) {
    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        RetrofitInstance.api.getBreakingNews(countryCode, pageNumber)

    suspend fun searchNews(searchQuery: String, pageNumber: Int) =
        RetrofitInstance.api.searchForNews(searchQuery, pageNumber)

    suspend fun ins(article: Article):Long{
        return db.getArticleDao().Insert(article)
    }

    fun getsavednews()= db.getArticleDao().getAllArticles()

    suspend fun del(article: Article)=db.getArticleDao().deleteArticle(article)

}