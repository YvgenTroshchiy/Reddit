package com.troshchii.reddit.ui.topnews

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.troshchii.reddit.R
import com.troshchii.reddit.core.extensions.inflate
import com.troshchii.reddit.core.extensions.inflater
import com.troshchii.reddit.core.extensions.setImageUrl
import com.troshchii.reddit.databinding.ListItemNewsBinding
import com.troshchii.reddit.ui.topnews.data.RedditPost
import java.util.LinkedList

class TopNewsAdapter(private val itemClick: (RedditPost, View) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var news: List<RedditPost> = LinkedList()
        set(value) {
            val diffResult = DiffUtil.calculateDiff(RedditPostDiffCallback(field, value));
            field = value
            diffResult.dispatchUpdatesTo(this)
        }

    var isLoadingMore = false
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = if (isLoadingMore) news.size + 1 else news.size

    override fun getItemViewType(position: Int): Int {
        return if (isLoadingMore && position == (itemCount - 1)) {
            ViewTypes.PROGRESS.code
        } else {
            ViewTypes.ITEM.code
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        when (viewType) {
            ViewTypes.ITEM.code -> NewsViewHolder(ListItemNewsBinding.inflate(parent.context.inflater(), parent, false))
            else -> ProgressViewHolder(parent.inflate(R.layout.list_item_progress))
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (isLoadingMore && position == (itemCount - 1)) return
        (holder as? NewsViewHolder)?.bind(news[position])
    }

    inner class NewsViewHolder(private val binding: ListItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(news: RedditPost) {
            binding.root.setOnClickListener { itemClick.invoke(news, binding.root) }

            binding.title.text = news.title
            binding.image.setImageUrl(news.thumbnail)
            binding.author.text = news.author
            binding.xHoursAgo.text = news.created_utc
            binding.numComments.text = news.numComments
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