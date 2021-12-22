package com.troshchii.reddit.ui.topnews

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.troshchii.reddit.core.extensions.setImageUrl
import com.troshchii.reddit.databinding.ListItemNewsBinding
import com.troshchii.reddit.ui.topnews.data.RedditPost

class NewsViewHolder(private val binding: ListItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(post: RedditPost, itemClick: (RedditPost, View) -> Unit) {
        with(binding) {

            root.transitionName = post.thumbnail
            root.setOnClickListener { itemClick.invoke(post, binding.root) }

            title.text = post.title
            image.setImageUrl(post.thumbnail)
            author.text = post.author
            xHoursAgo.text = post.created_utc
            numComments.text = post.numComments
        }
    }
}
