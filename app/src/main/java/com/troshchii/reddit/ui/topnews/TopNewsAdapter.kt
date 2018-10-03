package com.troshchii.reddit.ui.topnews

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.troshchii.reddit.databinding.NewsItemBinding
import com.troshchii.reddit.extensions.inflater
import java.util.*


class TopNewsAdapter(private val itemClick: (News) -> Unit) : RecyclerView.Adapter<TopNewsAdapter.BindingHolder>() {

    var news: List<News> = LinkedList()
        set(value) {
            data.clear()
            data.addAll(value)
            notifyDataSetChanged()
        }

    private val data = LinkedList<News>()

    override fun getItemCount() = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        BindingHolder(NewsItemBinding.inflate(parent.context.inflater()))

    override fun onBindViewHolder(holder: BindingHolder, position: Int) {
        holder.bind(data[position])
    }

    inner class BindingHolder(private val binding: NewsItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(news: News) {
            binding.root.setOnClickListener { itemClick.invoke(news) }
            binding.news = news
            binding.executePendingBindings()
        }
    }
}