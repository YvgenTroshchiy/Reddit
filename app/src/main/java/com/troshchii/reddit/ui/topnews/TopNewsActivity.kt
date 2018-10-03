package com.troshchii.reddit.ui.topnews

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import com.troshchii.reddit.R
import com.troshchii.reddit.extensions.*
import com.troshchii.reddit.core.vm.ViewModelFactory
import com.troshchii.reddit.di.scope.ActivityScoped
import com.troshchii.reddit.functional.Either
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.topnews_activity.*
import javax.inject.Inject


@ActivityScoped
class TopNewsActivity : AppCompatActivity() {

    private val tag = getLogTag<TopNewsActivity>()

    @Inject lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: TopNewsViewModel
    private lateinit var topNewsAdapter: TopNewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.topnews_activity)

        setupNewsList()

        viewModel = viewModel(viewModelFactory)

        observe(viewModel.topNews) {
            when (it) {
                is Either.Left -> {
                    logW(tag, "Error: $it")
                }
                is Either.Right -> {
                    topNewsAdapter.news = it.right
                    logI(tag, "Success")
                }
            }
        }
    }

    private fun setupNewsList() {
        newsList.addItemDecoration(DividerItemDecoration(this, VERTICAL))

        topNewsAdapter = TopNewsAdapter { logI(tag, "Click to the ${it.title}") }
        newsList.adapter = topNewsAdapter
    }
}