package com.troshchii.reddit.ui.topnews

import android.os.Bundle
import android.widget.Toast.LENGTH_LONG
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import com.troshchii.reddit.R
import com.troshchii.reddit.core.extensions.*
import com.troshchii.reddit.core.functional.Either
import com.troshchii.reddit.core.vm.ViewModelFactory
import com.troshchii.reddit.di.scope.ActivityScoped
import com.troshchii.reddit.ui.newsdetails.NewsDetailsActivity
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.topnews_activity.*
import javax.inject.Inject


@ActivityScoped
class TopNewsActivity : AppCompatActivity() {

    private val tag = getLogTag<TopNewsActivity>()

    @Inject lateinit var viewModelFactory: ViewModelFactory

    //TODO: Maybe remove lateinit?
    private lateinit var viewModel: TopNewsViewModel
    private lateinit var topNewsAdapter: TopNewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.topnews_activity)

        setupNewsList()

        //TODO: Use 1 extention instead 2
        viewModel = viewModel(viewModelFactory)

        observe(viewModel.topNews) {
            when (it) {
                is Either.Left -> {
                    logW(tag, "Error: $it")
                    toast("it", LENGTH_LONG)
                }
                is Either.Right -> {
                    logI(tag, "Success")
                    topNewsAdapter.news = it.right
                }
            }
        }
    }

    private fun setupNewsList() {
        newsList.addItemDecoration(DividerItemDecoration(this, VERTICAL))

        topNewsAdapter = TopNewsAdapter {
            logI(tag, "Click to the: ${it.title}, ${it.imageUrl}")
            it.imageUrl?.let { imageUrl ->
                startActivity(NewsDetailsActivity.newIntent(this, imageUrl))
            }
        }

        newsList.adapter = topNewsAdapter
    }
}