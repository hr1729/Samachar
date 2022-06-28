package com.androiddevs.mvvmnewsapp.fragments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class NewsviewmodelproviderFactory(val repository: NewsRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return viewModel(repository) as T
    }
}