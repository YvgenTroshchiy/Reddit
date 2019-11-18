package com.troshchii.reddit.ui.topnews

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.troshchii.reddit.core.extensions.inflater
import com.troshchii.reddit.databinding.NewsItemBinding
import com.troshchii.reddit.ui.topnews.data.RedditPost


class TopNewsAdapter(
    private val itemClick: (RedditPost) -> Unit
) : PagedListAdapter<RedditPost, TopNewsAdapter.BindingHolder>(diffCallback) {

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<RedditPost>() {
            override fun areItemsTheSame(oldItem: RedditPost, newItem: RedditPost) = (oldItem.id == newItem.id)

            override fun areContentsTheSame(oldItem: RedditPost, newItem: RedditPost) = (oldItem == newItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        BindingHolder(NewsItemBinding.inflate(parent.context.inflater(), parent, false))

    override fun onBindViewHolder(holder: BindingHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    inner class BindingHolder(private val binding: NewsItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(news: RedditPost) {
            binding.root.setOnClickListener { itemClick.invoke(news) }
            binding.news = news
            binding.executePendingBindings()
        }
    }
}