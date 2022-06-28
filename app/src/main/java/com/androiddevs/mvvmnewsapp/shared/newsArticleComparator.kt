package com.androiddevs.mvvmnewsapp.shared

import androidx.recyclerview.widget.DiffUtil
import com.androiddevs.mvvmnewsapp.data.Article

class NewsArticleComparator : DiffUtil.ItemCallback<Article>() {

    override fun areItemsTheSame(oldItem: Article, newItem: Article) =
        oldItem.url == newItem.url

    override fun areContentsTheSame(oldItem: Article, newItem: Article) =
        oldItem == newItem
}