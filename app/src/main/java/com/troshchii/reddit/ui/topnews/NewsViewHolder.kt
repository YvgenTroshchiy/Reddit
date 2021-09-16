package com.troshchii.reddit.ui.topnews

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.troshchii.reddit.core.extensions.setImageUrl
import com.troshchii.reddit.databinding.ListItemNewsBinding
import com.troshchii.reddit.ui.topnews.data.RedditPost

class NewsViewHolder(private val binding: ListItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(news: RedditPost, itemClick: (RedditPost, View) -> Unit) {
        with(binding) {

            root.transitionName = news.thumbnail
            root.setOnClickListener { itemClick.invoke(news, binding.root) }

            title.text = news.title
            image.setImageUrl(news.thumbnail)
            author.text = news.author
            xHoursAgo.text = news.created_utc
            numComments.text = news.numComments
        }
    }
}
