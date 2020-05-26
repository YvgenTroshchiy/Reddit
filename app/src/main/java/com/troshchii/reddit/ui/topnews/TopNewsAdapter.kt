package com.troshchii.reddit.ui.topnews

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.troshchii.reddit.R
import com.troshchii.reddit.core.extensions.inflater
import com.troshchii.reddit.databinding.NewsItem2ColumnsBinding
import com.troshchii.reddit.ui.topnews.data.RedditPost
import java.util.*


class TopNewsAdapter(private val itemClick: (RedditPost) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var news: List<RedditPost> = LinkedList()
        set(value) {
            val diffResult = DiffUtil.calculateDiff(RedditPostDiffCallback(field, value));
            field = value
            diffResult.dispatchUpdatesTo(this)
        }

    override fun getItemCount() = news.size

    //TODO: Update
    override fun getItemViewType(position: Int) = when (news[position]) {
        is RedditPost -> ViewTypes.ITEM.code
        else -> ViewTypes.PROGRESS.code
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        when (viewType) {
            ViewTypes.ITEM.code -> NewsViewHolder(NewsItem2ColumnsBinding.inflate(parent.context.inflater(), parent, false))
            else -> ProgressViewHolder(parent.context.inflater().inflate(R.layout.list_item_progress, parent, false))
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? NewsViewHolder)?.bind(news[position])
    }

    inner class NewsViewHolder(private val binding: NewsItem2ColumnsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(news: RedditPost) {
            binding.root.setOnClickListener { itemClick.invoke(news) }
            binding.news = news
            binding.executePendingBindings()
        }
    }

    inner class ProgressViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    companion object {
        enum class ViewTypes(val code: Int) { ITEM(0), PROGRESS(1) }

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