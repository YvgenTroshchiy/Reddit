package com.troshchii.reddit.ui.topnews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.transition.MaterialElevationScale
import com.troshchii.reddit.R
import com.troshchii.reddit.core.Async
import com.troshchii.reddit.core.Fail
import com.troshchii.reddit.core.Success
import com.troshchii.reddit.core.extensions.getLogTag
import com.troshchii.reddit.core.extensions.logI
import com.troshchii.reddit.core.extensions.logW
import com.troshchii.reddit.core.extensions.observe
import com.troshchii.reddit.core.extensions.toast
import com.troshchii.reddit.databinding.TopnewsFragmentBinding
import com.troshchii.reddit.ui.topnews.data.RedditPost
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.FlowCollector
import org.koin.androidx.viewmodel.ext.android.viewModel

class TopNewsFragment : Fragment() {

    private val logTag = getLogTag<TopNewsFragment>()

    private lateinit var binding: TopnewsFragmentBinding
    private val viewModel: TopNewsViewModel by viewModel()

    private lateinit var topNewsAdapter: TopNewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logI(logTag, "onCreate")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        logI(logTag, "onCreateView")
        binding = TopnewsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        logI(logTag, "onViewCreated")

        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }
    }

    @OptIn(InternalCoroutinesApi::class)
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        logI(logTag, "onActivityCreated")

        setupNewsList()

//        viewLifecycleOwner.observe(viewModel.topNews) {
//            when (it) {
//                is Either.Left -> {
//                    logW(logTag, "Error: $it")
//                    context?.toast("${it.left}", Toast.LENGTH_LONG)
//                }
//                is Either.Right -> {
//                    logI(logTag, "Success")
//                    topNewsAdapter.news = it.right
//                }
//            }
//        }

        lifecycleScope.launchWhenStarted {
            viewModel.topNews.collect(object : FlowCollector<Async<List<RedditPost>>> {
                override suspend fun emit(it: Async<List<RedditPost>>) {
                    when (it) {
                        is Fail -> {
                            logW(logTag, "Error: $it")
                            context?.toast("${it.error.message}", Toast.LENGTH_LONG)
                        }
                        is Success -> {
                            logI(logTag, "Success")
                            topNewsAdapter.news = it.invoke()
                        }
                        else -> {

                        }
                    }
                }
            })
        }

        viewLifecycleOwner.observe(viewModel.isLoading) {
            logI(logTag, "isLoading: $it")
            binding.progressBar.visibility = if (it == true) View.VISIBLE else View.GONE
            binding.newsList.visibility = if (it == true) View.GONE else View.VISIBLE
        }
        viewLifecycleOwner.observe(viewModel.isLoadingMore) {
            logI(logTag, "isLoadingMore: $it")
            topNewsAdapter.isLoadingMore = it ?: false
        }
    }

    private fun setupNewsList() {
        topNewsAdapter = TopNewsAdapter { redditPost, view -> openDetailsActivity(redditPost, view) }

        with(binding.newsList) {
            val layoutManager = GridLayoutManager(context, 2)
            this.layoutManager = layoutManager
            adapter = topNewsAdapter

            layoutManager.spanSizeLookup = object : SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return when (topNewsAdapter.getItemViewType(position)) {
                        //number of columns of the grid
                        TopNewsAdapter.Companion.ViewTypes.ITEM.code -> 1
                        TopNewsAdapter.Companion.ViewTypes.PROGRESS.code -> 2
                        else -> -1
                    }
                }
            }

            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    val visibleItemCount = layoutManager.childCount
                    val lastVisibleItem = layoutManager.findLastVisibleItemPosition()
                    val totalItemCount = layoutManager.itemCount

                    viewModel.listScrolled(visibleItemCount, lastVisibleItem, totalItemCount)
                }
            })
        }
    }

    private fun openDetailsActivity(it: RedditPost, view: View) {
        logI(logTag, "Click to the: ${it.title}, ${it.imageUrl}")

        exitTransition = MaterialElevationScale(false).apply {
            duration = resources.getInteger(R.integer.reply_motion_duration_large).toLong()
        }
        reenterTransition = MaterialElevationScale(true).apply {
            duration = resources.getInteger(R.integer.reply_motion_duration_large).toLong()
        }

        val extras = FragmentNavigatorExtras(view to getString(R.string.transition_image))
        val action = TopNewsFragmentDirections.actionTopNewsFragmentToNewsDetailsFragments(it)

        findNavController().navigate(action, extras)
    }
}