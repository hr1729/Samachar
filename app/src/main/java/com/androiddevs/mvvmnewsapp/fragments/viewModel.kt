package com.androiddevs.mvvmnewsapp.fragments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androiddevs.mvvmnewsapp.data.newsResponse
import com.androiddevs.mvvmnewsapp.shared.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class viewModel(val repo:NewsRepository): ViewModel() {
    val breakingNews: MutableLiveData<Resource<newsResponse>> = MutableLiveData()
    val searchNews: MutableLiveData<Resource<newsResponse>> = MutableLiveData()
    var searchNewsPage = 1
    var breakingNewsPage = 1

init {
    getBreakingNews("us")
}
    fun getBreakingNews(countryCode: String)= viewModelScope.launch {
        breakingNews.postValue(Resource.Loading())
        val response = repo.getBreakingNews(countryCode, breakingNewsPage)
        breakingNews.postValue(handleBreakingNewsResponse(response))
    }
    fun searchNews(searchQuery: String) = viewModelScope.launch {
        searchNews.postValue(Resource.Loading())
        val response = repo.searchNews(searchQuery, searchNewsPage)
        searchNews.postValue(handleSearchNewsResponse(response))
    }
    private fun handleBreakingNewsResponse(response: Response<newsResponse>) : Resource<newsResponse> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
    //////////////////
    private fun handleSearchNewsResponse(response: Response<newsResponse>) : Resource<newsResponse> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

}