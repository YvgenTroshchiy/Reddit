package com.troshchii.reddit.ui.newsdetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import com.troshchii.reddit.R
import com.troshchii.reddit.core.extensions.getLogTag
import kotlinx.android.synthetic.main.newsdetails_activity.*


private const val EXTRA_URL = "extra_url"

private var Intent.imageUrl: String
    get() = this.getStringExtra(EXTRA_URL)
    set(value) {
        putExtra(EXTRA_URL, value)
    }


class NewsDetails : AppCompatActivity() {

    private val tag = getLogTag<NewsDetails>()

    companion object {
        fun newIntent(context: Context, imageUrl: String): Intent {
            return Intent(context, NewsDetails::class.java).apply {
                this.imageUrl = imageUrl
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.newsdetails_activity)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        showImage()
    }

    // TODO: Create VM. Set Placeholder
    private fun showImage() {
        Picasso.get()
            .load(intent.imageUrl)
            .into(image)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

}