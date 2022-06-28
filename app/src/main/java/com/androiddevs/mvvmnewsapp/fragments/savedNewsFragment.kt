package com.androiddevs.mvvmnewsapp.fragments

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androiddevs.mvvmnewsapp.R
import com.androiddevs.mvvmnewsapp.data.Article
import com.androiddevs.mvvmnewsapp.databinding.FragmentSavedNewsBinding
import com.androiddevs.mvvmnewsapp.shared.SwipeGesture
import com.androiddevs.mvvmnewsapp.shared.newsArticleAdapter
import com.google.android.material.snackbar.Snackbar

class savedNewsFragment: Fragment(R.layout.fragment_saved_news) {
    lateinit var viewmodel:viewModel
    lateinit var binding: FragmentSavedNewsBinding
    lateinit var newsAdapter: newsArticleAdapter
    lateinit var navController: NavController
    lateinit var articles:List<Article>
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewmodel=(activity as MainActivity).viewmodel
        binding= FragmentSavedNewsBinding.bind(view)
        newsAdapter=newsArticleAdapter()
        binding.rvSavedNews.apply {
            adapter=newsAdapter
            layoutManager=LinearLayoutManager(activity)
        }
        val swipeGesture=object:SwipeGesture(requireContext()){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val article =articles[position]
               when(direction){
                   ItemTouchHelper.LEFT -> {
                       viewmodel.del(article)
                       Snackbar.make(view,"Article Delteted Successfully",Snackbar.LENGTH_LONG).show()
                   }
               }
            }

        }
        val touchHelper=ItemTouchHelper(swipeGesture)
        touchHelper.attachToRecyclerView(binding.rvSavedNews)

        newsAdapter.setOnItemClickListener {
            val bundle= Bundle().apply {
                putSerializable("article",it)
            }
            findNavController().navigate(R.id.action_savedNewsFragment_to_articleFragment,bundle)
        }
        viewmodel.getnews().observe(viewLifecycleOwner){
            newsAdapter.submitList(it)
            articles=it
        }
    }
}