package com.troshchii.reddit.ui.newsdetails

import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import com.troshchii.reddit.R
import com.troshchii.reddit.core.extensions.getLogTag
import com.troshchii.reddit.core.extensions.logD
import com.troshchii.reddit.core.extensions.setImageUrl
import com.troshchii.reddit.core.extensions.toast
import kotlinx.android.synthetic.main.newsdetails_activity.*

private const val EXTRA_TITLE = "extra_title"
private const val EXTRA_URL = "extra_url"

private var Intent.title: String?
    get() = getStringExtra(EXTRA_TITLE)
    set(value) {
        putExtra(EXTRA_TITLE, value)
    }

private var Intent.imageUrl: String?
    get() = getStringExtra(EXTRA_URL)
    set(value) {
        putExtra(EXTRA_URL, value)
    }

//TODO: convert to fragment and use navigation library
class NewsDetailsActivity : AppCompatActivity() {

    private val tag = getLogTag<NewsDetailsActivity>()

    companion object {
        fun newIntent(context: Context, title: String, imageUrl: String): Intent {
            return Intent(context, NewsDetailsActivity::class.java).apply {
                this.title = title
                this.imageUrl = imageUrl
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.newsdetails_activity)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //TODO: update
        supportActionBar?.title = intent.title
        supportActionBar?.subtitle = intent.title

        showImage()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.newsdetails_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) =
        when (item.itemId) {
            android.R.id.home -> {
                finishAfterTransition()
                true
            }
            R.id.menu_save -> downloadImage()
            else -> false
        }

    private fun downloadImage(): Boolean {
        logD(tag, "Save Image")

        if (Environment.getExternalStorageState() != Environment.MEDIA_MOUNTED) {
            toast("External storage not mounted!")
        } else {
            val request = DownloadManager.Request(Uri.parse(intent.imageUrl))
                .setTitle(intent.title)
                .setDescription("Downloading")
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                .setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES, intent.title + ".png")

            (getSystemService(DOWNLOAD_SERVICE) as DownloadManager).enqueue(request)
        }
        return true
    }

    // TODO: Create VM. Set Placeholder
    private fun showImage() {
        intent.imageUrl?.let {
            val url = HtmlCompat.fromHtml(it, HtmlCompat.FROM_HTML_MODE_LEGACY)
            image.setImageUrl(url.toString())
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}