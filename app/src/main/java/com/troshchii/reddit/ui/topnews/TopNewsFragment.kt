package com.troshchii.reddit.ui.topnews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.troshchii.reddit.R
import com.troshchii.reddit.core.extensions.*
import com.troshchii.reddit.core.functional.Either
import com.troshchii.reddit.ui.newsdetails.NewsDetailsActivity
import kotlinx.android.synthetic.main.topnews_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class TopNewsFragment : Fragment() {

    private val logTag = getLogTag<TopNewsFragment>()

    private val viewModel: TopNewsViewModel by viewModel()

    private lateinit var topNewsAdapter: TopNewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logI(logTag, "onCreate")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        logI(logTag, "onCreateView")
        return inflater.inflate(R.layout.topnews_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupNewsList()

        viewLifecycleOwner.observe(viewModel.topNews) {
            when (it) {
                is Either.Left -> {
                    logW(logTag, "Error: $it")
                    context!!.toast("it", Toast.LENGTH_LONG)
                }
                is Either.Right -> {
                    logI(logTag, "Success")
                    topNewsAdapter.news = it.right
                }
            }
        }
    }

    private fun setupNewsList() {
        newsList.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

        topNewsAdapter = TopNewsAdapter {
            logI(logTag, "Click to the: ${it.title}, ${it.imageUrl}")
            it.imageUrl?.let { imageUrl ->
                startActivity(NewsDetailsActivity.newIntent(context!!, imageUrl))
            }
        }

        newsList.adapter = topNewsAdapter
    }
}