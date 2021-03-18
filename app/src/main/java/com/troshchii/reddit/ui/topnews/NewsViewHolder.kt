package com.troshchii.reddit.ui.topnews

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.troshchii.reddit.core.extensions.setImageUrl
import com.troshchii.reddit.databinding.ListItemNewsBinding
import com.troshchii.reddit.ui.topnews.data.RedditPost

class NewsViewHolder(private val binding: ListItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(news: RedditPost, itemClick: (RedditPost, View) -> Unit) {
        binding.root.transitionName = news.thumbnail
        binding.root.setOnClickListener { itemClick.invoke(news, binding.root) }

        binding.title.text = news.title
        binding.image.setImageUrl(news.thumbnail)
        binding.author.text = news.author
        binding.xHoursAgo.text = news.created_utc
        binding.numComments.text = news.numComments
    }
}