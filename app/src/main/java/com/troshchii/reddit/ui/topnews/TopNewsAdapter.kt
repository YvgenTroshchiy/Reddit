package com.troshchii.reddit.ui.topnews

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.troshchii.reddit.core.extensions.inflater
import com.troshchii.reddit.databinding.NewsItem2ColumnsBinding
import com.troshchii.reddit.ui.topnews.data.RedditPost
import java.util.*


class TopNewsAdapter(private val itemClick: (RedditPost) -> Unit) : RecyclerView.Adapter<TopNewsAdapter.BindingHolder>() {

    var news: List<RedditPost> = LinkedList()
        set(value) {
            val diffResult = DiffUtil.calculateDiff(RedditPostDiffCallback(field, value));
            field = value
            diffResult.dispatchUpdatesTo(this);
        }

    override fun getItemCount() = news.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        BindingHolder(NewsItem2ColumnsBinding.inflate(parent.context.inflater(), parent, false))

    override fun onBindViewHolder(holder: BindingHolder, position: Int) {
        holder.bind(news[position])
    }

    inner class BindingHolder(private val binding: NewsItem2ColumnsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(news: RedditPost) {
            binding.root.setOnClickListener { itemClick.invoke(news) }
            binding.news = news
            binding.executePendingBindings()
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<RedditPost>() {
            override fun areItemsTheSame(old: RedditPost, new: RedditPost) = (old.id == new.id)
            override fun areContentsTheSame(old: RedditPost, new: RedditPost) = (old == new)
        }
    }

    class RedditPostDiffCallback(
        private val old: List<RedditPost>,
        private val new: List<RedditPost>
    ) : DiffUtil.Callback() {

        override fun getOldListSize() = old.size
        override fun getNewListSize() = new.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return old[oldItemPosition].id == new[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return old[oldItemPosition] == new[newItemPosition]
        }
    }
}