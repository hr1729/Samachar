package com.androiddevs.mvvmnewsapp.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.androiddevs.mvvmnewsapp.MainActivity
import com.androiddevs.mvvmnewsapp.R

class SearchNewsFragment: Fragment(R.layout.fragment_search_news) {
    lateinit var viewmodel:viewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewmodel=(activity as MainActivity).viewmodel
    }
}