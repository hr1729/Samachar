package com.androiddevs.mvvmnewsapp.fragments

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.View
import androidx.fragment.app.Fragment
import com.androiddevs.mvvmnewsapp.MainActivity
import com.androiddevs.mvvmnewsapp.R

class ArticleFragment() : Fragment(R.layout.fragment_article) {
    lateinit var viewmodel:viewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewmodel=(activity as MainActivity).viewmodel
    }
}