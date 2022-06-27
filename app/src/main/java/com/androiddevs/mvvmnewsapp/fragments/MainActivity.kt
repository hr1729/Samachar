package com.androiddevs.mvvmnewsapp.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.androiddevs.mvvmnewsapp.R
import com.androiddevs.mvvmnewsapp.database.articleDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var viewmodel: viewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val db=NewsRepository(articleDatabase(this))

        val viewmodelprovider= NewsviewmodelproviderFactory(db)

        viewmodel=ViewModelProvider(this,viewmodelprovider)[viewModel::class.java]

        bottomNavigationView.setupWithNavController(newsNavHostFragment.findNavController())
    }
}
