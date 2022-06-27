package com.androiddevs.mvvmnewsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.androiddevs.mvvmnewsapp.database.articleDatabase
import com.androiddevs.mvvmnewsapp.fragments.NewsRepository
import com.androiddevs.mvvmnewsapp.fragments.viewModel
import com.androiddevs.mvvmnewsapp.fragments.viewmodelproviderFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var viewmodel: viewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val db=NewsRepository(articleDatabase(this))
        val viewmodelprovider=viewmodelproviderFactory(db)
        viewmodel=ViewModelProvider(this,viewmodelprovider).get(viewmodel::class.java)

        bottomNavigationView.setupWithNavController(newsNavHostFragment.findNavController())
    }
}
