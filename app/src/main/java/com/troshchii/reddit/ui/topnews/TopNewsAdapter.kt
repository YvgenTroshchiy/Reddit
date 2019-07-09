package com.troshchii.reddit.ui.topnews

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.troshchii.reddit.core.extensions.inflater
import com.troshchii.reddit.databinding.NewsItemBinding
import com.troshchii.reddit.ui.topnews.data.RedditPost
import java.util.*


class TopNewsAdapter(private val itemClick: (RedditPost) -> Unit) : RecyclerView.Adapter<TopNewsAdapter.BindingHolder>() {

    var news: List<RedditPost> = LinkedList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = news.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        BindingHolder(NewsItemBinding.inflate(parent.context.inflater(), parent, false))

    override fun onBindViewHolder(holder: BindingHolder, position: Int) {
        holder.bind(news[position])
    }

    inner class BindingHolder(private val binding: NewsItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(news: RedditPost) {
            binding.root.setOnClickListener { itemClick.invoke(news) }
            binding.news = news
            binding.executePendingBindings()
        }
    }
}