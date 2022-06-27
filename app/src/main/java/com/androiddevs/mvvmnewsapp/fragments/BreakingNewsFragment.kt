package com.androiddevs.mvvmnewsapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.androiddevs.mvvmnewsapp.R
import com.androiddevs.mvvmnewsapp.data.newsResponse
import com.androiddevs.mvvmnewsapp.databinding.FragmentBreakingNewsBinding
import com.androiddevs.mvvmnewsapp.shared.Resource
import com.androiddevs.mvvmnewsapp.shared.newsArticleAdapter
import kotlinx.android.synthetic.main.fragment_breaking_news.*

class BreakingNewsFragment: Fragment(R.layout.fragment_breaking_news) {
    lateinit var viewmodel:viewModel
    lateinit var newsAdapter: newsArticleAdapter
    lateinit var binding: FragmentBreakingNewsBinding

    val TAG = "Breakingerror"
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentBreakingNewsBinding.bind(view)
        newsAdapter= newsArticleAdapter()
        binding.apply {
            rvBreakingNews.apply {
                adapter=newsAdapter
                layoutManager = LinearLayoutManager(activity)
            }
        }
       // setupRecyclerView()
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
//                        Log.e(TAG, "An error occured: $message")
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
        Toast.makeText(context,"loading",Toast.LENGTH_LONG).show()
        binding.paginationProgressBar.visibility = View.VISIBLE
    }
}