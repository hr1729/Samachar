package com.androiddevs.mvvmnewsapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.androiddevs.mvvmnewsapp.R
import com.androiddevs.mvvmnewsapp.databinding.FragmentBreakingNewsBinding
import com.androiddevs.mvvmnewsapp.databinding.ItemArticlePreviewBinding
import com.androiddevs.mvvmnewsapp.shared.Resource
import com.androiddevs.mvvmnewsapp.shared.newsArticleAdapter

class BreakingNewsFragment: Fragment(R.layout.fragment_breaking_news) {
    lateinit var viewmodel:viewModel
    lateinit var newsAdapter: newsArticleAdapter
    lateinit var binding: FragmentBreakingNewsBinding
    lateinit var navController: NavController
    val TAG = "Breakingerror"
    lateinit var bnd:ItemArticlePreviewBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentBreakingNewsBinding.bind(view)
        newsAdapter= newsArticleAdapter()
        navController=Navigation.findNavController(view)
        newsAdapter.setOnItemClickListener {
            val bundle= Bundle().apply {
                putSerializable("article",it)
            }
            findNavController().navigate(R.id.action_breakingNewsFragment_to_articleFragment,bundle)
        }
        binding.apply {
            rvBreakingNews.apply {
                adapter=newsAdapter
                layoutManager = LinearLayoutManager(activity)
            }
        }

        viewmodel=(activity as MainActivity).viewmodel
        viewmodel.breakingNews.observe(viewLifecycleOwner, Observer {
            when(it) {
                is Resource.Success -> {
                    hideProgressBar()
                    it.data?.let {
                        newsAdapter.submitList(it.articles)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    it.message?.let { message ->
                       Log.e(TAG, "An error occured: $message")

                    }
                }
                is Resource.Loading -> {
                    Log.e(TAG, "An error occured:")
                    showProgressBar()
                }
            }
        })
    }
    private fun hideProgressBar() {
        binding.paginationProgressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        binding.paginationProgressBar.visibility = View.VISIBLE
    }
}